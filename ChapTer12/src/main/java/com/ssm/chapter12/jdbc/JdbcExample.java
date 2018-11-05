package com.ssm.chapter12.jdbc;

import com.ssm.chapter12.pojo.Role;

import java.sql.*;

public class JdbcExample {
	
	public Role getRole(Long id) {
		Role role = null;
		// ����JDBC����
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// ע����������
			Class.forName("com.mysql.jdbc.Driver");
			// ��ȡ����
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chapter12", "root", "123456");
			// Ԥ����SQL
			ps = con.prepareStatement("select id, role_name, note from t_role where id = ?");
			// ���ò���
			ps.setLong(1, id);
			// ִ��SQL
			rs = ps.executeQuery();
			// ��װ���������POJO
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getLong(1));
				role.setRoleName(rs.getString(2));
				role.setNote(rs.getString(3));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// �쳣����
			e.printStackTrace();
		} finally {
			// �ر����ݿ�������Դ
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return role;
	}
}
