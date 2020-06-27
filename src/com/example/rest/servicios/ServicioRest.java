package com.example.rest.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.rest.dao.UsuarioModel;
import com.example.rest.entidades.Usuario;

@Path("/servicios")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ServicioRest {
	private static final Log log = LogFactory.getLog(ServicioRest.class);
	private UsuarioModel daoUser = new UsuarioModel();

	@GET
	@Path("/login")
	public Response login(Usuario obj) {
		log.info("login rest ");
		return Response.ok(daoUser.login(obj)).build();
	}

	@GET
	@Path("/usuario")
	public Response listarTodos() {
		log.info("listarTodos rest ");
		return Response.ok(daoUser.listarTodos()).build();
	}

	@POST
	@Path("/usuario")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response registra(Usuario obj) {
		log.info("Registra usuario " + obj.getIdUsuario());
		if (daoUser.insertaUsuario(obj) >0)
			return Response.ok().build();
		else
			return Response.notModified().build();
	}
	
	@PUT
	@Path("/usuario")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response atualiza(Usuario obj) {
		log.info("Actualiza usuario " + obj.getIdUsuario());
		if (daoUser.actualizaUsuario(obj) >0)
			return Response.ok().build();
		else
			return Response.notModified().build();
	}

	@DELETE
	@Path("/usuario/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response elimina(@PathParam("id")int id) {
		log.info("Elimina usuario " + id);
		if (daoUser.eliminaUsuario(id) >0)
			return Response.ok().build();
		else
			return Response.notModified().build();
	}

}