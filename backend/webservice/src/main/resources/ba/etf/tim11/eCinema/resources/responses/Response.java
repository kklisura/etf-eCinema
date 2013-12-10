package ba.etf.tim11.eCinema.resources.responses;

import org.codehaus.jackson.annotate.JsonPropertyOrder;


@JsonPropertyOrder({"status", "message", "moreInfo"})
public class Response 
{
	private int status;
	private String message;
	private String moreInfo;
	
	
	public Response(int status, String message, String moreInfo)
	{
		this.status = status;
		this.message = message;
		this.moreInfo = moreInfo;
	}
	
	public Response(int status, String message)
	{
		this.status = status;
		this.message = message;
	}
	
	public static Response success() {
		return new Response(200, "Success");
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMoreInfo() {
		return moreInfo;
	}
	
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}
	
}
