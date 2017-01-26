package amazonExcelDataFiles;

import java.util.ArrayList;

import utils.excelUtils;

public class ReadUserInformationExcelFile {

	public ArrayList<String> userName;
	public ArrayList<String> email;
	public ArrayList<String> password;
	public ArrayList<String> confirmPassword;
	
	
	public ReadUserInformationExcelFile(ArrayList<String> userName, ArrayList<String> email, 
			ArrayList<String> password,  ArrayList<String> confirmPassword){
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	/**
	 * UserDatabase
	 */
	public ReadUserInformationExcelFile(){
		userName  = new ArrayList<String>();
		email  = new ArrayList<String>();
		password  = new ArrayList<String>();
		confirmPassword  = new ArrayList<String>();
	}
	
	/**
     * Set data
     * @param userDataFile
     * @param userSheet
     * @param opParams
     * @throws Exception
     */
	public void setData(String userDataFile, String userSheet){
		System.out.println("file is:" + userDataFile);
		String[][] testData;
		try {
			testData = excelUtils.getExcelDataFromSource(userDataFile,userSheet);
			for(int i = 0; i<testData.length; i++)
			{	
				userName.add(testData[i][1]);
				email.add(testData[i][2]);
				password.add(testData[i][3]);
				confirmPassword.add(testData[i][4]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get user name by index
	 * @param index
	 * @return userName.get(index)
	 */
	public String getUserNameByIndex(int index){
		return userName.get(index);
	}
	
	/**
	 * Get email by index
	 * @param index
	 * @return email.get(index)
	 */
	public String getEmailByIndex(int index){
		return email.get(index);
	}
	
	/**
	 * Get password by index
	 * @param index
	 * @return password.get(index)
	 */
	public String getPasswordByIndex(int index){
		return password.get(index);
	}
	
	/**
	 * Get confirmPassword by index
	 * @param index
	 * @return confirmPassword.get(index)
	 */
	public String getConfirmPasswordByIndex(int index){
		return confirmPassword.get(index);
	}
}
