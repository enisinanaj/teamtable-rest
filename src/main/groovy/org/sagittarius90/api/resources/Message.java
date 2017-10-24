package org.sagittarius90.api.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sagittarius90.api.model.MessageModel;
import org.sagittarius90.api.utils.IdUtils;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Message {

	@Path("/{messageId}")
	@GET
	public Response get(@PathParam("messageId") String messageId) {
		Long realId = IdUtils.getInstance().decodeId(messageId);

		MessageModel messageModel = new MessageModel();
		messageModel.setId(realId);
		messageModel.setBody("body");
		messageModel.setFrom("from email");
		messageModel.setTo("to email");
		messageModel.setHeader("");
		
		return Response.ok().entity(messageModel).build();
	}
}
