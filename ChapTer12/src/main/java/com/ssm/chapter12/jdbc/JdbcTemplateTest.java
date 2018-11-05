package com.ssm.chapter12.jdbc;

import com.ssm.chapter12.pojo.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class JdbcTemplateTest {
	public static void main(String[] args) {
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cfg.xml");
	    JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
	    
	    JdbcTemplateTest test = new JdbcTemplateTest();
	    test.getRoleByConnectionCallback(jdbcTemplate, 1L);
	    test.getRoleByStatementCallback(jdbcTemplate, 1L);
	    test.insertRole(jdbcTemplate);
	    List roleList = test.findRole(jdbcTemplate, "role");
	    System.out.println(roleList.size());
	    Role role = new Role();
	    role.setId(1L);
	    role.setRoleName("update_role_name_1");
	    role.setNote("update_note_1");
	    test.updateRole(jdbcTemplate, role);
	    test.deleteRole(jdbcTemplate, 1L);
	}


	/***
	 * �����ɫ
	 * @param jdbcTemplate --ģ��
	 * @return Ӱ������
	 */
	public int insertRole(JdbcTemplate jdbcTemplate) {
	    String roleName = "role_name_1";
	    String note = "note_1";
	    String sql = "insert into t_role(role_name, note) values(?, ?)";
	    return jdbcTemplate.update(sql, roleName, note);
	}

	/**
	 * ɾ����ɫ
	 * @param jdbcTemplate -- ģ��
	 * @param id -- ��ɫ��ţ�����
	 * @return Ӱ������
	 */
	public int deleteRole(JdbcTemplate jdbcTemplate, Long id) {
	    String sql = "delete from t_role where id=?";
	    return jdbcTemplate.update(sql, id);
	}

	public int updateRole(JdbcTemplate jdbcTemplate, Role role) {
	    String sql = "update t_role set role_name=?, note = ? where id = ?";
	    return jdbcTemplate.update(sql, role.getRoleName(), role.getNote(), role.getId());
	}

	/**
	 * ��ѯ��ɫ�б�
	 * @param jdbcTemplate--ģ��
	 * @param roleName --��ɫ����
	 * @return ��ɫ�б�
	 */
	public List<Role> findRole(JdbcTemplate jdbcTemplate, String roleName) {
	    String sql = "select id, role_name, note from t_role where role_name like concat('%',?, '%')";
	    Object[] params = {roleName};//��֯����
	    //ʹ��RowMapper�ӿ���֯���أ�ʹ��lambda���ʽ��
	    List<Role> list = jdbcTemplate.query(sql, params, (ResultSet rs, int rowNum) -> {
	        Role result = new Role();
	        result.setId(rs.getLong("id"));
	        result.setRoleName(rs.getString("role_name"));
	        result.setNote(rs.getString("note"));
	        return result;
	    });
	    return list;
	}
	
	/**
	 * ʹ��ConnectionCallback�ӿڽ��лص�
	 * @param jdbcTemplate ģ��
	 * @param id ��ɫ���
	 * @return ���ؽ�ɫ
	 */
	public Role getRoleByConnectionCallback(JdbcTemplate jdbcTemplate, Long id) {
		Role role = null;
	    //����д��Java 8��Lambda���ʽ�������ʹ�õͰ汾��Java����Ҫʹ��ConnectionCallback������
		role = jdbcTemplate.execute((Connection con) -> {
			Role result = null;
			String sql = "select id, role_name, note from t_role where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new Role();
				result.setId(rs.getLong("id"));
				result.setNote(rs.getString("note"));
				result.setRoleName(rs.getString("role_name"));
			}
			return result;
		});
		return role;
	}

	/**
	 * ʹ��StatementCallback�ӿڽ��лص�
	 * @param jdbcTemplateģ��
	 * @param id��ɫ���
	 * @return���ؽ�ɫ
	 */
	public Role getRoleByStatementCallback(JdbcTemplate jdbcTemplate, Long id) {
		Role role = null;
	     //����д��Java 8��lambda���ʽ�������ʹ�õͰ汾��Java����Ҫʹ��StatementCallback��������
		role = jdbcTemplate.execute((Statement stmt) -> {
			Role result = null;
			String sql = "select id, role_name, note from t_role where id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				result = new Role();
				result.setId(rs.getLong("id"));
				result.setNote(rs.getString("note"));
				result.setRoleName(rs.getString("role_name"));
			}
			return result;
		});
		return role;
	}
}
