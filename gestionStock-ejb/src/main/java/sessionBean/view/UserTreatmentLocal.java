package sessionBean.view;

import java.util.List;

import javax.mail.AuthenticationFailedException;
import entity.ChangeUserStatus;
import entity.GrantUser;
import entity.Historisation;
import entity.Status;
import entity.User;
import tool.CustomException;

public interface UserTreatmentLocal {

	public List<User> getUserList() throws SecurityException;

	public List<User> getUserActifList() throws CustomException, SecurityException;

	public String createUser(String login, String grant, String password, String email, String firstName,
			String lastName, User userAdmin) throws SecurityException, AuthenticationFailedException;

	public boolean deleteUser(String login) throws SecurityException, CustomException;

	public boolean testModifyUser(String login, String grant, String email, String firstName, String lastName)
			throws CustomException, SecurityException;

	public String modifyUser(String login, String grant, String email, String firstName, String lastName)
			throws SecurityException, CustomException;

	public List<GrantUser> getGrantUserList() throws CustomException, SecurityException;

	public List<Status> getStatusList() throws CustomException, SecurityException;

	public String modifyPassword(String login, String password) throws SecurityException, AuthenticationFailedException;

	public String changeStatusUser(User userAdmin, String login, String statusName) throws CustomException;
	
	public List<ChangeUserStatus> getChangeUserStatusByUser(String login) throws CustomException ;
	

		

}