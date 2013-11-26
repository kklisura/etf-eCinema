package ba.etf.tim11.eCinema.resources;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import ba.etf.tim11.eCinema.dao.DaoFactory;
import ba.etf.tim11.eCinema.dao.ReceiptDao;
import ba.etf.tim11.eCinema.dao.impl.JDBCDaoFactory;
import ba.etf.tim11.eCinema.models.Receipt;
import ba.etf.tim11.eCinema.resources.privileges.Privilege;
import ba.etf.tim11.eCinema.resources.responses.BadRequestException;
import ba.etf.tim11.eCinema.resources.responses.ResourceNotFoundException;
import ba.etf.tim11.eCinema.resources.responses.Response;
import ba.etf.tim11.eCinema.utils.ResourceUtil;


@Path("receipts")
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptResource 
{
	private DaoFactory daoFactory;
	private ReceiptDao receiptDao;
	
	public ReceiptResource()
	{
		this.daoFactory = JDBCDaoFactory.getInstance();
		this.receiptDao = daoFactory.getReceipt();
	}
	
	@GET
	@Privilege("List")
	public List<Receipt> getAllReceipts() 
	{ 
		return receiptDao.findAll();
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Create")
	public Receipt createNewReceipt(MultivaluedMap<String, String> formParams) 
	{
		if (!ResourceUtil.hasAll(formParams, "totalPrice", "discount")) 
		{
			throw new BadRequestException("You are missing some fields.");
		}
		
		Receipt receipt = new Receipt();
		
		BigDecimal totalPrice = new BigDecimal(formParams.getFirst("totalPrice"));
		receipt.setTotalPrice(totalPrice);
		
		BigDecimal discount = new BigDecimal(formParams.getFirst("discount"));
		receipt.setDiscount(discount);
		
		receiptDao.insert(receipt);
		
		return receipt;
	}
	
	@POST
	@Path("{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Privilege("Update")
	public Response updateReceipt(@PathParam("id") Integer id, MultivaluedMap<String, String> formParams) 
	{
		Receipt receipt = receiptDao.find(id);
		if(receipt == null){
			throw new ResourceNotFoundException("Receipt not found.");
		}
		
		if (formParams.getFirst("totalPrice") != null)
		{
			BigDecimal totalPrice = new BigDecimal(formParams.getFirst("totalPrice"));
			receipt.setTotalPrice(totalPrice);
		}
		
		if (formParams.getFirst("discount") != null)
		{
			BigDecimal discount = new BigDecimal(formParams.getFirst("discount"));
			receipt.setDiscount(discount);
		}
		
		receiptDao.update(receipt);
		
		return Response.success();
	}
	
	@DELETE
	@Path("{id}")
	@Privilege("Delete")
	public Response deleteReceipt(@PathParam("id") Integer id) 
	{
		Receipt receipt = receiptDao.find(id);
		if (receipt == null) {
			throw new ResourceNotFoundException("Receipt not found.");
		}
		
		receiptDao.delete(receipt);
		
		return Response.success();
	}
	

}
