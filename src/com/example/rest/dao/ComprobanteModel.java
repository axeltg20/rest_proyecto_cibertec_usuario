package com.example.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.rest.entidades.Comprobante;
import com.example.rest.util.MySqlDBConexion;

public class ComprobanteModel {
	private static final Log log = LogFactory.getLog(UsuarioModel.class);

	public List<Comprobante> listarTodos() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<Comprobante> lista = new ArrayList<Comprobante>();
		try {
			String sql = "select * from comprobante";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			Comprobante obj = null;
			while (rs.next()) {
				obj = new Comprobante();
				obj.setIdcomprobante(rs.getInt(1));
				obj.setFechaRegistro(rs.getString(2));
				obj.setFechaPago(rs.getString(3));
				obj.setEstado(rs.getString(4));
				obj.setIdpedido(rs.getInt(5));
				obj.setIdcliente(rs.getInt(6));
				obj.setIdusuario(rs.getInt(7));
				lista.add(obj);
			}
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return lista;
	}

	public int insertaComprobante(Comprobante obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			String sql = "insert into comprobante values(null,?,?,?,?,?,?)";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getFechaRegistro());
			pstm.setString(2, obj.getFechaPago());
			pstm.setString(3, obj.getEstado());
			pstm.setInt(4, obj.getIdpedido());
			pstm.setInt(5, obj.getIdcliente());
			pstm.setInt(6, obj.getIdusuario());
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (pstm != null)
					pstm.close();
			} catch (SQLException e1) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return salida;
	}

	public int actualizaComprobante(Comprobante obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			String sql = "update comprobante set fechaRegistro =?, fechaPago =?, estado= ?, idpedido = ?, idcliente = ?, idusuario = ? where idcomprobante =? ";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(7, obj.getIdcomprobante());
			pstm.setString(1, obj.getFechaRegistro());
			pstm.setString(2, obj.getFechaPago());
			pstm.setString(3, obj.getEstado());
			pstm.setInt(4, obj.getIdpedido());
			pstm.setInt(5, obj.getIdcliente());
			pstm.setInt(6, obj.getIdusuario());
			log.info(pstm);

			salida = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (pstm != null)
					pstm.close();
			} catch (SQLException e1) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return salida;
	}

	public int eliminaComprobante(int obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			String sql = "delete from comprobante where idcomprobante =?";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, obj);
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (pstm != null)
					pstm.close();
			} catch (SQLException e1) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return salida;
	}
}
