
// ============================================
//  Globals
// ============================================

App = Ember.Application.create({
    currentPath: '',
    carouselLoaded: false,
    carouselData: [],
    tags: [],
    states: [],
    cinema: null,
    session: null,
    user: null,
    isLoggedIn: false
});



App.deferReadiness();

var session = localStorage.getItem('session');
if (session != null) {

    var userProperties = JSON.parse(localStorage.getItem('user'));
    var userObject = Ember.Object.create();
    userObject.setProperties(userProperties);

    App.set('user', userObject);
    App.set('session', session);
    App.set('isLoggedIn', true);

    Ember.$.ajaxSetup({
        headers: {
            'X-Auth': session
        }
    });
}

App.advanceReadiness();


// Helpers
App.deleteAjax = function(url, data) {
    return Ember.$.ajax({
        url: url + '/' + data.id,
        type: 'DELETE',
        dataType: 'json'
    });
}


// App controller
App.ApplicationController = Ember.Controller.extend({
    updateCurrentPath: function() {
        App.set('currentPath', this.get('currentPath'));
    }.observes('currentPath')
}),


// ============================================
// Router
// ============================================

App.Router.map(function() {

    this.route('register');

    this.route('newest');
    this.route('top');
    this.resource('tags', {path: 'tags/:tag'});
    this.resource('theaters');

    this.resource('content', {path: 'content/:id'}, function() {
        this.route('watch');
        this.route('theater');
    });


    this.resource('user', function() {
        this.route('history');
        this.route('settings', {auth: true});
        //this.route('billing');
        //this.route('receipts');
    });

    this.resource('admin', function() {
        this.route('users');
        this.route('groups');
        this.route('access');
        this.route('contents');
    });

    this.route('test');
});



App.ApplicationController = Ember.Controller.extend({
    error: ''
});


// ==========================================
//  Theaters
// ==========================================

App.TheatersRoute = Ember.Route.extend({
    model: function() {
        var result = new Ember.Object();
        Ember.$.getJSON('api/v1/contents?filter=theaters&limit=3', function(response) {
            for(var i=0; i < response.entity.length; i++) {
                response.entity[i].image = 'http://localhost:8080/eCinema/images/thumb/' + response.entity[i].id + '.png';
            }
            result.set('this_week_content', response.entity);
        });        
        Ember.$.getJSON('api/v1/contents?filter=theaters&limit=3&offset=3', function(response) {
            for(var i=0; i < response.entity.length; i++) {
                response.entity[i].image = 'http://localhost:8080/eCinema/images/thumb/' + response.entity[i].id + '.png';
            }
            result.set('playing_content', response.entity);
        });
        return result;
    }
});


// ==========================================
//  Register
// ==========================================

App.RegisterRoute = Ember.Route.extend({
    model: function() {
        return {};
    },
    activate: function() {
        if (this.get('controller') != undefined) {
            this.get('controller').resetMessage();
        }
    }
});

App.RegisterController = Ember.ObjectController.extend({
    needs: ['error-box'],
    actions: {
        sign_up: function(user) {
            var controller = this;
            Ember.$.post('api/v1/users', user).done(function(user) {
                controller.showMessage('You successfully registered. You can now login.', true);
            }).fail(function(response) {
                controller.showMessage('You are missing some fileds', false);
            });
        }
    }
});




App.TagsController = Ember.ArrayController.extend({

    has_content: function() {
        var rows = this.get('model').get('rows');
        if (rows != undefined && rows.length > 0) {
            return true;
        }
        return false;
    }.property('model.rows')

});

App.TagsRoute = Ember.Route.extend({
    model: function(options) {

        this.controllerFor('tags').set('tag', options.tag);

        var result = new Ember.A();
        var that = this;
        Ember.$.getJSON('api/v1/contents?filter=' + options.tag + '&limit=6', function(response) {
            var rows = [];
            for(var i=0; i < response.entity.length; i++) {
                response.entity[i].image = 'http://localhost:8080/eCinema/images/thumb/' + response.entity[i].id + '.png';
            }
            for(var i = 0; i < response.entity.length / 3; i++) {
                var row_content = [];
                for(var j = 0; j < 3; j++) {
                    if (response.entity[i*3 + j] == undefined) {
                        break;
                    }
                    row_content.push(response.entity[i*3 + j]);
                }
                rows.push(row_content);
            }
            that.set('has_content', response.entity.length > 0);
            result.set('rows', rows);
        });

        return result;   
    }
});

App.ApplicationRoute = Ember.Route.extend({
    model: function() {
        Ember.$.getJSON('api/v1/tags', function(response) {
            Ember.set('App.tags', response);
        });
        Ember.$.getJSON('api/v1/states', function(response) {
            Ember.set('App.states', response);
        });
        Ember.$.getJSON('api/v1/cinemas/1', function(response) {
            Ember.set('App.cinema', response);
        });
        return new Ember.Object();
    },
    actions: {
        do_modal: function(name, controller) {
            var controller = controller || 'application';
            this.render(name, { into: 'application', outlet: 'modal', view: 'modal', controller: controller });
        },
        close: function() {
            this.disconnectOutlet({outlet: 'modal', parentView: 'application'});
        },
        go_back: function() {
            history.back();
        },
        logout: function() {
            App.set('user', null);
            App.set('session', null);
            App.set('isLoggedIn', false);
            localStorage.removeItem('user');
            localStorage.removeItem('session');

            this.transitionTo('index');
        },
        login: function() {
            var data = {};

            data.username = document.getElementById('username').value;
            data.password = document.getElementById('password').value;

            var that = this;
            var appController = this.controllerFor('application');

            Ember.$.post("api/v1/login", data, function(response) {
                if (response.auth == null) 
                {
                    // Login error
                    appController.set('error', 'Wrong username and/or password!');
                } else {
                    // Login success
                    appController.set('error', '');

                    var xAuthHeader = response.user.username + ':' + response.auth;

                    App.set('user', response.user);
                    App.set('session', xAuthHeader);
                    App.set('isLoggedIn', true);

                    localStorage.setItem('user', JSON.stringify(response.user));
                    localStorage.setItem('session', xAuthHeader);

                    Ember.$.ajaxSetup({
                        headers: {
                            'X-Auth': xAuthHeader
                        }
                    });

                    that.send('close');
                }
            });
        },
    }
});

App.IndexRoute = Ember.Route.extend({
    model: function() {

        var result = Ember.Object.create({
            newest_content: [],
            rated_content: [],
        });

        Ember.$.getJSON('api/v1/contents?filter=top&limit=3', function(response) {

            var carousel = [];

            for(var i = 0; i < response.entity.length; i++) {
                carousel.pushObject({title: response.entity[i].title, 
                                    content: response.entity[i].shortDescription,
                                    image: 'http://localhost:8080/eCinema/images/top/' + response.entity[i].id + '.jpg' })
            }

            App.set('carouselData', carousel);
            App.set('carouselLoaded', true);
        });

        Ember.$.getJSON('api/v1/contents?filter=newest&limit=3', function(response) {
            for(var i=0; i < response.entity.length; i++) {
                response.entity[i].image = 'http://localhost:8080/eCinema/images/thumb/' + response.entity[i].id + '.png';
            }
            result.set('newest_content', response.entity);
        });

        Ember.$.getJSON('api/v1/contents?filter=rated&limit=3', function(response) {
            for(var i=0; i < response.entity.length; i++) {
                response.entity[i].image = 'http://localhost:8080/eCinema/images/thumb/' + response.entity[i].id + '.png';
            }
            result.set('rated_content', response.entity);
        });

        return result;
    }
});

App.NewestRoute = Ember.Route.extend({
    loaded: 0,
    model: function() {
        
        var result = new Ember.A();
        var that = this;
        Ember.$.getJSON('api/v1/contents?filter=newest&limit=6', function(response) {
            var rows = [];
            for(var i=0; i < response.entity.length; i++) {
                response.entity[i].image = 'http://localhost:8080/eCinema/images/thumb/' + response.entity[i].id + '.png';
            }
            for(var i = 0; i < response.entity.length / 3; i++) {
                var row_content = [];
                for(var j = 0; j < 3; j++) {
                    if (response.entity[i*3 + j] == undefined) {
                        break;
                    }
                    row_content.push(response.entity[i*3 + j]);
                }
                rows.push(row_content);
            }
            that.set('loaded', response.entity.length);
            result.set('rows', rows);
        });

        return result;
    },
    actions: {
        load_more: function() {
            var offset = this.get('loaded');
            var that = this;

            Ember.$.getJSON('api/v1/contents?filter=newest&offset=' + offset + '&limit=6', function(response) {
                var result = that.modelFor('newest');

                for(var i = 0; i < response.entity.length / 3; i++) {
                    var row_content = [];
                    for(var j = 0; j < 3; j++) {
                        if (response.entity[i*3 + j] == undefined) {
                            break;
                        }
                        row_content.push(response.entity[i*3 + j]);
                    }
                    result.rows.pushObject(row_content);
                }

                that.set('loaded', offset + response.entity.length);
            });
        }
    }
});

App.TopRoute = Ember.Route.extend({
    loaded: 0,

    model: function() {
        
        var result = new Ember.Object();
        var that = this;
        Ember.$.getJSON('api/v1/contents?filter=rated', function(response) {
            var rows = [];
            for(var i=0; i < response.entity.length; i++) {
                response.entity[i].image = 'http://localhost:8080/eCinema/images/thumb/' + response.entity[i].id + '.png';
            }
            for(var i = 0; i < response.entity.length / 3; i++) {
                var row_content = [];
                for(var j = 0; j < 3; j++) {
                    if (response.entity[i*3 + j] == undefined) {
                        break;
                    }
                    row_content.push(response.entity[i*3 + j]);
                }
                rows.push(row_content);
            }
            that.set('loaded', response.entity.length);
            result.set('rows', rows);
        });

        return result;
    },
    actions: {
        load_more: function() {
            var offset = this.get('loaded');
            var that = this;

            Ember.$.getJSON('api/v1/contents?filter=rated&offset=' + offset + '&limit=6', function(response) {
                var result = that.modelFor('rated');

                for(var i = 0; i < response.entity.length / 3; i++) {
                    var row_content = [];
                    for(var j = 0; j < 3; j++) {
                        if (response.entity[i*3 + j] == undefined) {
                            break;
                        }
                        row_content.push(response.entity[i*3 + j]);
                    }
                    result.rows.pushObject(row_content);
                }

                that.set('loaded', offset + response.entity.length);
            });
        }
    }
});




// ==========================================
//  Admin
// ==========================================
App.AdminRoute = Ember.Route.extend({
    beforeModel: function() {
        this.transitionTo('admin.users');
    }
});



// ==========================================
//  Admin Users
// ==========================================

App.AdminUsersRoute = Ember.Route.extend({
    model: function() {
        var route = this;
        Ember.$.getJSON('api/v1/groups', function(response) {
            route.set('groups', response.entity);
        });
        Ember.$.getJSON('api/v1/roles', function(response) {
            route.set('roles', response);
        });
        return new Ember.RSVP.Promise(function(resolve) {
            Ember.$.getJSON('api/v1/users', function(response) {
                for(var i = 0; i < response.entity.length; i++) {
                    response.entity[i].createdAt = moment(response.entity[i].createdAt).format("MMM Do YYYY");
                }
                resolve(response);
            });
        });
    },
    setupController: function(controller, model) {
        this._super(controller, model);
        controller.set('groups', this.get('groups'));
        controller.set('roles', this.get('roles'));
    },
    renderTemplate: function(controller, model) {
        this.render('admin-user-list');
    }, 
    activate: function() {
        if (this.get('controller') != undefined) {
            this.get('controller').resetMessage();
        }
    },
    actions: {
        show_add_user: function() {
            this.get('controller').resetMessage();
            this.get('controller').set('user', {});
            this.get('controller').set('user_roles', []);
            this.get('controller').set('user_groups', []);
            this.render('admin-user-add');
        },
        show_edit_user: function() {
            this.get('controller').resetMessage();
            this.render('admin-user-edit');
        },
        show_list: function() {
            this.render('admin-user-list');
        }
    }
});

App.AdminUsersController = Ember.ObjectController.extend({
    needs: ['error-box'],
    groups: [],
    roles: [],
    actions: {
        edit_user: function(user) {
            var controller = this;
            this.set('user', user);
            this.set('user_roles', []);
            this.set('user_groups', []);
            Ember.$.getJSON('api/v1/roles/users/' + user.id, function(roles) {
                controller.set('user_roles', roles);
            });
            Ember.$.getJSON('api/v1/groups/users/' + user.id, function(groups) {
                controller.set('user_groups', groups);
            });
            this.send('show_edit_user');
        },
        add_user: function(user) {
            var controller = this;
            user.groups = this.get('user_groups');
            user.roles = this.get('user_roles');
            Ember.$.post('api/v1/users', user).done(function(user) {
                user.createdAt = moment(user.createdAt).format("MMM Do YYYY");
                controller.showMessage('User successfully added.', true);
                controller.get('model.entity').addObject(user);
            }).fail(function(response) {
                controller.showMessage(response.message, false);
            });
        },
        save_user: function(user) {
            var controller = this;
            user.groups = this.get('user_groups');
            user.roles = this.get('user_roles');
            Ember.$.post('api/v1/users/' + user.id, user).done(function(response) {
                controller.showMessage('User settings updated.', true);
            }).fail(function(response) {
                controller.showMessage(response.message, false);
            });
        },
        delete_user: function(user) {
            var controller = this;
            var users = controller.get('model.entity');
            if (confirm('Are you sure you want to delete this user?')) {
                App.deleteAjax('api/v1/users', user).always(function() {
                    users.removeObject(user);
                });
            }
        },
    }
});


// ==========================================
//  Admin Groups
// ==========================================

App.AdminGroupsRoute = Ember.Route.extend({
    model: function() {
        var route = this;
        Ember.$.getJSON('api/v1/roles', function(response) {
            route.set('roles', response);
        });
        return new Ember.RSVP.Promise(function(resolve) {
            Ember.$.getJSON('api/v1/groups', function(response) {
                for(var i = 0; i < response.entity.length; i++) {
                    response.entity[i].createdAt = moment(response.entity[i].createdAt).format("MMM Do YYYY");
                }
                resolve(response);
            });
        });
    },
    setupController: function(controller, model) {
        this._super(controller, model);
        controller.set('roles', this.get('roles'));
    },
    renderTemplate: function(controller, model) {
        this.render('admin-group-list');
    }, 
    activate: function() {
        if (this.get('controller') != undefined) {
            this.get('controller').resetMessage();
        }
    },
    actions: {
        show_add_group: function() {
            this.get('controller').resetMessage();
            this.get('controller').set('group', {});
            this.get('controller').set('group_roles', []);
            this.render('admin-group-add');
        },
        show_edit_group: function() {
            this.get('controller').resetMessage();
            this.render('admin-group-edit');
        },
        show_list: function() {
            this.render('admin-group-list');
        }
    }
});

App.AdminGroupsController = Ember.ObjectController.extend({
    needs: ['error-box'],
    roles: [],
    actions: {
        edit_group: function(group) {
            var controller = this;
            this.set('group', group);
            this.set('group_roles', []);
            Ember.$.getJSON('api/v1/roles/groups/' + group.id, function(roles) {
                controller.set('group_roles', roles);
            });
            this.send('show_edit_group');
        },
        add_group: function(group) {
            var controller = this;
            group.roles = this.get('group_roles');
            Ember.$.post('api/v1/groups', group).done(function(group) {
                group.createdAt = moment(group.createdAt).format("MMM Do YYYY");
                controller.showMessage('Group successfully added.', true);
                controller.get('model.entity').addObject(group);
            }).fail(function(response) {
                controller.showMessage(response.message, false);
            });
        },
        save_group: function(group) {
            var controller = this;
            group.roles = this.get('group_roles');
            Ember.$.post('api/v1/groups/' + group.id, group).done(function(response) {
                controller.showMessage('Group settings updated.', true);
            }).fail(function(response) {
                controller.showMessage(response.message, false);
            });
        },
        delete_group: function(group) {
            var controller = this;
            var groups = controller.get('model.entity');
            if (confirm('Are you sure you want to delete this group?')) {
                App.deleteAjax('api/v1/groups', group).always(function() {
                    groups.removeObject(group);
                });
            }
        },
    }
});


// ==========================================
//  Admin Access
// ==========================================

App.AdminAccessRoute = Ember.Route.extend({
    model: function() {
        var route = this;
        Ember.$.getJSON('api/v1/privilegetypes', function(response) {
            route.set('privilegetypes', response);
        });
        Ember.$.getJSON('api/v1/resources', function(response) {
            route.set('resources', response);
        });
        Ember.$.getJSON('api/v1/roles', function(response) {
            route.set('roles', response);
        });
        return Ember.$.getJSON('api/v1/roles?privileges=true', function(response) {
            return response;
        });
    },
    setupController: function(controller, model) {
        this._super(controller, model);
        controller.set('privilegetypes', this.get('privilegetypes'));
        controller.set('resources', this.get('resources'));
        controller.set('roles', this.get('roles'));
    },
    renderTemplate: function(controller, model) {
        this.render('admin-access-list');
    }, 
    activate: function() {
        if (this.get('controller') != undefined) {
            this.get('controller').resetMessage();
        }
    },
    actions: {
        show_add_privilege: function() {
            this.get('controller').resetMessage();
            //this.get('controller').set('group', {});
            //this.get('controller').set('group_roles', []);
            this.render('admin-privilege-add');
        },
        show_list: function() {
            this.render('admin-group-list');
        }
    }
});

App.AdminAccessController = Ember.ObjectController.extend({
    needs: ['error-box'],
    actions: {
        add_privilege: function(privilege) {
            var controller = this;
            privilege.roles = this.get('group_roles');
            Ember.$.post('api/v1/groups', group).done(function(group) {
                controller.showMessage('Group successfully added.', true);
                controller.get('model.entity').addObject(group);
            }).fail(function(response) {
                controller.showMessage(response.message, false);
            });
        },
        delete_group: function(role) {
            var controller = this;
            var roles = controller.get('model.entity');
            if (confirm('Are you sure you want to delete this group?')) {
                App.deleteAjax('api/v1/roles', group).always(function() {
                    roles.removeObject(role);
                });
            }
        },
    }
});


// ==========================================
//  Admin Contents
// ==========================================

App.AdminContentsRoute = Ember.Route.extend({
    model: function() {
        var route = this;
        Ember.$.getJSON('api/v1/tags', function(response) {
            route.set('tags', response);
        });
        return new Ember.RSVP.Promise(function(resolve) {
            Ember.$.getJSON('api/v1/contents', function(response) {
                for(var i = 0; i < response.entity.length; i++) {
                    response.entity[i].createdAt = moment(response.entity[i].createdAt).format("MMM Do YYYY");
                }
                resolve(response);
            });
        });
    },
    setupController: function(controller, model) {
        this._super(controller, model);
        controller.set('tags', this.get('tags'));
    },
    renderTemplate: function(controller, model) {
        this.render('admin-contents-list');
    }, 
    activate: function() {
        if (this.get('controller') != undefined) {
            this.get('controller').resetMessage();
        }
    },
    actions: {
        show_add_content: function() {
            this.get('controller').resetMessage();
            this.get('controller').set('content1', {});
            this.get('controller').set('content_tags', []);
            this.render('admin-content-add');
        },
        show_edit_content: function() {
            this.get('controller').resetMessage();
            this.render('admin-content-edit');
        },
        show_list: function() {
            this.render('admin-contents-list');
        }
    }
});

App.AdminContentsController = Ember.ObjectController.extend({
    needs: ['error-box'],
    actions: {
        edit_content: function(content) {
            var controller = this;

            Ember.$.getJSON('api/v1/contents/' + content.id, function(response) {
                controller.set('content_tags', response.tags);
            });

            this.set('content1', content);
            this.send('show_edit_content');
        },
        add_content: function(content) {
            var controller = this;
            content.tags = this.get('content_tags');
            Ember.$.post('api/v1/contents', content).done(function(Content) {
                controller.showMessage('Content successfully added.', true);
                controller.get('model.entity').addObject(content);
            }).fail(function(response) {
                controller.showMessage(response.message, false);
            });
        },
        save_content: function(content) {
            var controller = this;
            content.tags = this.get('content_tags');
            Ember.$.post('api/v1/contents/' + content.id, content).done(function(response) {
                controller.showMessage('Content settings updated.', true);
            }).fail(function(response) {
                controller.showMessage(response.message, false);
            });
        },
        delete_content: function(content) {
            var controller = this;
            var contents = controller.get('model.entity');
            if (confirm('Are you sure you want to delete this content?')) {
                App.deleteAjax('api/v1/contents', content).always(function() {
                    contents.removeObject(content);
                });
            }
        },
    }
});





// =================================================
//  User History
// =================================================

App.UserHistoryRoute = Ember.Route.extend({
    model: function() {
        var user = App.get('user');
        return Ember.$.getJSON('api/v1/users/' + user.id + '/history', function(response) {

            for(var i = 0; i < response.length; i++) {
                switch(response[i].userActionType.id) {
                    case 1: response[i].what = 'registered first time.'; break;
                    case 2: response[i].what = 'left a comment'; break;
                    case 3: response[i].what = 'logged in.'; break;
                    case 4: response[i].what = 'rated a movie'; break;
                    case 5: response[i].what = 'watched a movie'; break;
                    case 6: response[i].what = 'made a reservation on'; break;
                }
                response[i].when = moment(response[i].time).calendar();
            }

            return response.reverse();
        });
    }
});




// =================================================
//  User Settings
// =================================================

App.UserSettingsRoute = Ember.Route.extend({
    model: function() {
        var user = App.get('user');
        var result = new Ember.Object();
        Ember.$.getJSON('api/v1/users/' + user.id, function(response) {
            result.setProperties(response);
            App.set('user', result);
        });
        return result;
    },
    actions: {
        save: function(user) {
            var controller = this.get('controller');
            var _user = JSON.parse(JSON.stringify(user));
            Ember.$.post('api/v1/users/' + user.id, _user).done(function(response) {
                controller.showMessage('User settings updated.', true);
            }).fail(function(response) {
                controller.showMessage(response.message, false);
            });
        }
    },
    activate: function() {
        if (this.get('controller') != undefined)
            this.get('controller').resetMessage();
    }
});

App.UserSettingsController = Ember.ObjectController.extend({
    needs: ['error-box']
})







App.AdminReservationsRoute = Ember.Route.extend({
    model: function() {
        return Ember.$.getJSON('api/v1/reservations', function(response) {
            return response;
        });
    }
});





// ============================================
//  Content
// ============================================

App.ContentRoute = Ember.Route.extend({
    model: function(params) {
        var route = this;
        return Ember.RSVP.Promise(function(resolve) {
            Ember.$.getJSON('api/v1/contents/' + params.id, function(content) {
                var result = new Ember.Object();
                result.setProperties(content);
                result.set('score', 0);
                route.setupRating(result);
                route.setupComments(result);
                resolve(result);
            });
        });
    },
    afterModel: function(model) {
        if (model == undefined || model.id == undefined) {
            return;
        }
        this.setupRating(model);
        this.setupComments(model);
    },
    setupRating: function(content) {
        Ember.$.getJSON('api/v1/contents/' + content.id + '/rating', function(response) {
            Ember.set(content, 'score', Math.round(response * 100) / 100);
        });
    },  
    setupComments: function(content) {
        var comments = this.controllerFor('comments');
        comments.set('content_id', content.id);

        Ember.$.getJSON('api/v1/comments/' + content.id + '?limit=6', function(response) {
            comments.set('limit', 6);
            comments.set('comments', response.entity);
        });
    }
});

App.ContentController = Ember.ObjectController.extend();

App.ContentIndexRoute = Ember.Route.extend({
    model: function(params) {
        return this.modelFor('content');
    }
});

App.ContentIndexController = Ember.ObjectController.extend({});

App.ContentIndexView = Ember.View.extend({
    didInsertElement: function() {
        this.$(".rating").raty({ 
            score: function() {
                return $(this).attr('data-score');
            },
            path: 'images/',
            starOff: 'star-off-big.png',
            starOn : 'star-on-big.png',
            width: 200,
            halfShow : false,
            click: function(score) {
                var contentId = $(this).attr('data-content');
                Ember.$.post('api/v1/contents/' + contentId + '/rating?mark=' + score);
            }
        });
    }
});

App.ContentTheaterRoute = Ember.Route.extend({
    model: function(params) {
        var content = this.modelFor('content');

        return new Ember.RSVP.Promise(function(resolve) { 
                Ember.$.getJSON('api/v1/contents/' + content.id + '/projections', function(response) {
                for(var i=0; i < response.length; i++) {
                    response[i].inSelect = response[i].projectionType.type + ' in ' +
                                           response[i].cinemaHall.title + ' on ' + 
                                           moment(response[i].time).calendar();
                }
                var result = new Ember.Object();
                result.set('projections', response);
                result.set('selectedProjection', response[0]);
                resolve(result);
            });
        });
        return result;
    }
});

App.ContentTheaterController = Ember.ObjectController.extend({
    selectedSeats: [],
    seatsTaken: [],
    onSeatsTaken: function() {
        var seats = this.get('seatsTaken');
        for(var i=0; i < seats.length; i++) {
            var sel = '.' + seats[i].seat + ' .inner';
            Ember.$(sel).addClass('taken');
        }
    }.observes('seatsTaken'),
    onSelectedProjection: function() {
        var projection = this.get('selectedProjection');
        var controller = this;
        controller.set('seatsTaken', []);

        Ember.$('.taken').removeClass('taken');

        Ember.$.getJSON('api/v1/projections/' + projection.id + '/takenseats', function(response) {
            var t = {'1': 'A', '2': 'B', '3': 'C', '4': 'D', '5': 'E', '6': 'F', '7': 'G'};

            for(var i=0;i<response.length;i++) {
                response[i].seat = t[response[i].row] + response[i].col;
            }

            controller.set('seatsTaken', response);
        });
    }.observes('model.selectedProjection'),
    actions: {
        make_reservation: function() {
            var t = {'A': 1, 'B': 2, 'C': 3, 'D': 4, 'E': 5, 'F': 6, 'G': 7};
            var seats = [];
            var selected = this.get('selectedSeats');
            for(var i=0;i<selected.length;i++) {
                seats.pushObject({row: t[selected[i][0]], col: selected[i][1]});
                
                var sel = '.' + selected[i] + ' .inner';
                Ember.$(sel).removeClass('selected');
                Ember.$(sel).addClass('taken');
            }
            var projection = this.get('model').get('selectedProjection');
            console.log(seats);
            Ember.$.post('api/v1/projections/' + projection.id + '/takeseats', {'seats': seats});
        },
        take_seat: function(sel) {
            var el = Ember.$('.' + sel + ' .inner');
            if (el.hasClass('taken')) {
                return;
            }
            if (el.hasClass('selected')) {
                this.get('selectedSeats').removeObject(sel);
                el.removeClass('selected');
            } else {
                this.get('selectedSeats').pushObject(sel);
                el.addClass('selected');
            }
        }
    }
});

App.ContentWatchRoute = Ember.Route.extend({
    model: function() {
        return this.modelFor('content');
    },
    afterModel: function() {
        return this.set('content', this.modelFor('content'));
    },
    setupController: function(controller, model) {
        this._super(controller, this.get('content'));
    }
});

App.ContentWatchController = Ember.ObjectController.extend({
    movieSource: function() {
        if (this.get('model').hasOwnProperty('get'))
            return "http://localhost/eCinema/" + this.get('model').get('id');
        return "http://localhost/eCinema/" + this.get('model').id;
    }.property('model')
});

App.ContentWatchView = Ember.View.extend({
    didInsertElement: function() {
        var popcorn = Popcorn("#video");
    }
});

App.CommentsController = Ember.ObjectController.extend({
    content_id: -1,
    limit: 6,
    offset: function() {
        return this.get('comments').length;
    }.property('comments.@each'),
    comments: [],
    noMoreComments: false,
    reverseComments: function() {
        return this.get('comments');
    }.property('comments.@each'),
    actions: {
        comment: function() {
            var comment = document.getElementById('comment').value;
            var content_id = this.get('content_id');
            var controller = this;
            Ember.$.post('api/v1/comments/' + content_id, {'text': comment}).done(function(response) {
                controller.get('comments').insertAt(0, response);
            });
        },
        load_more: function() {
            var controller = this;
            var content_id = controller.get('content_id');
            var limit = controller.get('limit');
            var offset = controller.get('offset');

            Ember.$.getJSON('api/v1/comments/' + content_id + '?limit=' + limit + '&offset=' + offset, function(response) {
                controller.set('offset', offset + response.offset);

                if (response.entity.length != 0) { 
                    controller.get('comments').pushObjects(response.entity);
                } else {
                    controller.set('noMoreComments', true);
                }
            });
        }
    }
});



// ============================================
// Views
// ============================================

App.CrossfadeView = {
    didInsertElement: function(){
        this.$().hide().fadeIn(400);
    },
    willDestroyElement: function(){
        this.$().slideDown(250);
    }
};


App.IndexView = Ember.View.extend(App.CrossfadeView);
App.RegisterView = Ember.View.extend(App.CrossfadeView, {
    didInsertElement: function(){
        Ember.$('#form').parsley({ 
            successClass: 'has-success', 
            errorClass: 'has-error' 
        });
    }
});

App.NewestView = Ember.View.extend(App.CrossfadeView);
App.TopView = Ember.View.extend(App.CrossfadeView);
App.NowView = Ember.View.extend(App.CrossfadeView);
App.TheatersView = Ember.View.extend(App.CrossfadeView);
App.ContentView = Ember.View.extend(App.CrossfadeView);
App.TagsView = Ember.View.extend(App.CrossfadeView);




// ============================================
// Helpers
// ============================================

App.SingleContentView = Ember.View.extend({    
    templateName: 'single_content',

    didInsertElement: function(){
        this.$('.description').dotdotdot();

        this.$('.content').hover(function() {
            $(this).addClass('hover');
        }, function() {
            $(this).removeClass('hover');
        });
    },
});


App.CarouselView = Ember.View.extend({    
    templateName: 'carousel',
    classNames: ['carousel', 'slide'],
    init: function() { 
        this._super.apply(this, arguments);
        // disable the data api from boostrap
        //$(document).off('.data-api');      
        // at least one item must have the active class, so we set the first here, and the class will be added by class binding
        var obj = this.get('content.firstObject');
        Ember.set(obj, 'isActive', true);
    },
    previousSlide: function() {
        this.$().carousel('prev');
    },
    nextSlide: function() {
        this.$().carousel('next');
    },
    didInsertElement: function() {
        this.$().carousel();
    },
    indicatorsView: Ember.CollectionView.extend({
        tagName: 'ol',
        classNames: ['carousel-indicators'],        
        contentBinding: 'parentView.content',
        itemViewClass: Ember.View.extend({
            click: function() {
                var $elem = this.get("parentView.parentView").$();
                $elem.carousel(this.get("contentIndex"));
            },
            template: Ember.Handlebars.compile(''),
            classNameBindings: ['content.isActive:active']            
        })
    }),
    itemsView: Ember.CollectionView.extend({        
        classNames: ['carousel-inner'],
        contentBinding: 'parentView.content',
        itemViewClass: Ember.View.extend({
            classNames: ['item'],
            classNameBindings: ['content.isActive:active'],
            template: Ember.Handlebars.compile('\
                <img {{bindAttr src="view.content.image"}} alt=""/>\
                <div class="carousel-caption">\
                    <h4>{{view.content.title}}</h4>\
                    <p>{{view.content.content}}</p>\
                </div>')
        })
    })
});

App.ModalView = Ember.View.extend({  
    didInsertElement: function() {
        Ember.run.next(this,function() {
            this.$('.modal, .modal-backdrop').addClass('in');
        });
    },
  
    layoutName: 'modal_layout',

    actions: {
        close: function() {
            var view = this;
            // use one of: transitionend webkitTransitionEnd oTransitionEnd MSTransitionEnd
            // events so the handler is only fired once in your browser
            this.$('.modal, .modal-backdrop').one("transitionend", function(ev) {
                view.controller.send('close');
            });
    
            this.$('.modal').removeClass('in');
        }
    },
});




App.CatImageView = Ember.View.extend({
    tagName: 'img',
    classNames: ['img-rounded'],
    attributeBindings: ['alt', 'style', 'src', 'width', 'height'],
    alt: 'Image: 360x200',
    width: function() {
        var isLoaded = this.get('isLoaded');
        if (isLoaded) {
            return '100%';
        }
        return '16';
    }.property('isLoaded'),
    height: function() {
        var isLoaded = this.get('isLoaded');
        if (isLoaded) {
            return '61%';
        }
        return '16';
    }.property('isLoaded'),
    isLoaded: false,
    style: 'margin-left: 172px; margin-right: 172px; margin-top: 92px; margin-bottom: 92px',
    src: 'images/loader.gif',

    didInsertElement: function () {
        if (this.get('src') != 'images/loader.gif') {
            return;
        }

        var image = new Image();        
        image.src = this.get('source');

        var view = this;
        image.onload = function() {
            if (view.isDestroyed)
                return;
            view.set('style', 'max-width: 360px; max-height: 200px');
            view.set('src', this.src);
            view.set('isLoaded', true);
        }
    }
});



App.AutoSuggestComponent = Ember.Component.extend({
    init: function() {
        this._super();
        this.set('selectedTags', []);
    },

    availableTags: function() {
        var tags = Ember.copy(this.get('tags'));
        var selectedTags = this.get('selectedTags');
        for(var i = 0; i < selectedTags.length; i++) {
            tags.removeObject(selectedTags[i]);
        }
        return tags;
    }.property('selectedTags.@each'),

    has_tags_to_select: function() {
        return this.get('availableTags').length > 0;
    }.property('availableTags.@each'),

    actions: {
        add_tag: function(tag) {
            this.get('selectedTags').pushObject(tag);
        },
        remove_tag: function(tag) {
            this.get('selectedTags').removeObject(tag);
        }
    }
});


// Error box helper; With added function to controllers
//

App.ErrorBoxController = Ember.ObjectController.extend({
    init: function() {
        this.set('message', '');
        this.set('isSuccessMessage', false);
        this.set('isErrorMessage', false);
    }
});

Ember.ObjectController.reopen({
    showMessage: function(message, isSuccessMessage) {
        this.set('controllers.error-box.message', message);
        this.set('controllers.error-box.isSuccessMessage', isSuccessMessage);
        this.set('controllers.error-box.isErrorMessage', !isSuccessMessage);
        window.scrollTo(0, 0);
    },
    resetMessage: function() {
        this.set('controllers.error-box.message', '');
        this.set('controllers.error-box.isSuccessMessage', false);
        this.set('controllers.error-box.isErrorMessage', false);
    }
});


Ember.Handlebars.helper('format-date', function( date ) {
    return moment(date).fromNow();
});