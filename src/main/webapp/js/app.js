
App = Ember.Application.create({
    currentPath: '',
});

// App controller
App.ApplicationController = Ember.Controller.extend({
    updateCurrentPath: function() {
        App.set('currentPath', this.get('currentPath'));
    }.observes('currentPath')
}),


/*



App.Person = DS.Model.extend({
  firstName: DS.attr('string'),
  birthday:  DS.attr('date')
});

App.Order = DS.Model.extend({
  lineItems: DS.hasMany('lineItem')
});

App.LineItem = DS.Model.extend({
  order: DS.belongsTo('order')
});

*/


// ============================================
// Router
// ============================================

App.Router.map(function() {
    this.route('register');

    this.route('newest');
    this.route('top');
    this.route('now');
    this.route('theaters');


    this.route('content', {path: 'content/:id'});


    this.resource('user', function() {
        this.route('history');
        this.route('settings');
        this.route('billing');

        this.resource('admin', function() {
            this.route('users');
        });
    });

});


// ============================================
// Routes
// ============================================

App.ApplicationRoute = Ember.Route.extend({
    actions: {
        open: function(name) {
            this.render(name, { into: 'application', outlet: 'modal', view: 'modal' });
        },
        close: function() {
            this.disconnectOutlet({outlet: 'modal', parentView: 'application'});
        },
        go_back: function() {
            history.back();
        },

        login_modal: function() {
            this.send('open','login_modal');
        },

        login: function() {
            // TODO(kklisura): Need to implement this.
            console.log("LOGIN: TODO")
        },
        sign_up: function() {
            // TODO(kklisura): Need to implement this.
            console.log("SIGN_UP: TODO")
        }
    }
});

App.RegisterRoute = Ember.Route.extend({
    model: function() {
        return Ember.$.getJSON('api/v1/states', function(response) {
            return response;
        });
    }
});

App.AdminUsersRoute = Ember.Route.extend({
    model: function() {
        return Ember.$.getJSON('api/v1/users', function(response) {
            return response;
        });
    }
});



// ============================================
// Views
// ============================================

App.CrossfadeView = {
    didInsertElement: function(){
        //called on creation
        this.$().hide().fadeIn(400);
    },
    willDestroyElement: function(){
        //called on destruction
        this.$().slideDown(250);
    }
};


App.IndexView = Ember.View.extend(App.CrossfadeView);
App.RegisterView = Ember.View.extend(App.CrossfadeView);
App.NewestView = Ember.View.extend(App.CrossfadeView);
App.TopView = Ember.View.extend(App.CrossfadeView);
App.NowView = Ember.View.extend(App.CrossfadeView);
App.TheatersView = Ember.View.extend(App.CrossfadeView);
App.ContentView = Ember.View.extend(App.CrossfadeView);





// ============================================
// Helpers
// ============================================

App.ModalView = Ember.View.extend({  
  didInsertElement: function() {
    Ember.run.next(this,function(){
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
  }
});