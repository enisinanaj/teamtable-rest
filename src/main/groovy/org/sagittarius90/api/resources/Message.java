package org.sagittarius90.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sagittarius90.api.model.MessageModel;
import org.sagittarius90.api.utils.IdUtils;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Message {

	@Path("/{messageId}")
	public MessageModel get(@PathParam("messageId") String messageId) {
		Long realId = IdUtils.decodeId(messageId);
		
		//TODO: implement
		MessageModel messageModel = new MessageModel();
		messageModel.setId(realId);
		messageModel.setBody("body");
		messageModel.setFrom("from email");
		messageModel.setTo("to email");
		messageModel.setHeader("");
		
		return messageModel;
	}
}
