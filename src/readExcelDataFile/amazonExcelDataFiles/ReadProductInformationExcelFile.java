package amazonExcelDataFiles;

import java.util.ArrayList;

import utils.excelUtils;

public class ReadProductInformationExcelFile {
	public ArrayList<String> searchText;
	public ArrayList<String> chooseProduct;
	public ArrayList<String> totalNumberOrder;


	public ReadProductInformationExcelFile(ArrayList<String> searchText, ArrayList<String> chooseProduct, 
			ArrayList<String> totalNumberOrder){
		this.searchText = searchText;
		this.chooseProduct = chooseProduct;
		this.totalNumberOrder = totalNumberOrder;
	}

	/**
	 * UserDatabase
	 */
	public ReadProductInformationExcelFile(){
		searchText  = new ArrayList<String>();
		chooseProduct  = new ArrayList<String>();
		totalNumberOrder  = new ArrayList<String>();
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
				searchText.add(testData[i][1]);
				chooseProduct.add(testData[i][2]);
				totalNumberOrder.add(testData[i][3]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get search text by index
	 * @param index
	 * @return searchText.get(index)
	 */
	public String getSearchTextByIndex(int index){
		return searchText.get(index);
	}

	/**
	 * Get choose Product by index
	 * @param index
	 * @return chooseProduct.get(index)
	 */
	public String getChooseProductByIndex(int index){
		return chooseProduct.get(index);
	}

	/**
	 * Get total Number Order by index
	 * @param index
	 * @return totalNumberOrder.get(index)
	 */
	public String getTotalNumberOrderByIndex(int index){
		return totalNumberOrder.get(index);
	}
}
