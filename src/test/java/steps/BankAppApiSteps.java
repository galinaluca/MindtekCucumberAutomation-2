package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.bankpojos.Account;
import utilities.ApiUtilsBank;

import java.util.Map;

public class BankAppApiSteps {
    Map<String,Object> accountData;
    Map<String,Object> updatedData;
    Response response;
    String accountId;

    @Given("user creates account with api call with data")
    public void user_creates_account_with_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        /*
        POST call->Request :1.Endpoint  2.Body using POJO objects
         */

        String endpoint="/api/account";
        Account account=new Account();
        accountData=dataTable.asMap(String.class,Object.class);
        account.setAccountType(accountData.get("accountType").toString());
        account.setBalance(Double.valueOf(accountData.get("balance").toString()));

        response=  ApiUtilsBank.postCall(endpoint,account); //POJO-> JSON
        accountId=response.body().jsonPath().getString("id");

    }

    @Then("user validates status code {int}")
    public void user_validates_status_code(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());

    }

    @Then("user validates that account is created with api get call")
    public void user_validates_that_account_is_created_with_api_get_call() {
        /*
        GET Request; 1.Endpoint
         */
        String endpoint="/api/accounts/" +accountId;
        response=ApiUtilsBank.getCall(endpoint);
       Account responseBody =response.body().as(Account.class);//JSON->POJO
        Assert.assertEquals(accountData.get("AccountType").toString(),responseBody.getAccountType());
        Assert.assertEquals(Double.valueOf(accountData.get("balance").toString()),responseBody.getBalance());

    }

    @When("user updates account with  api call with data")
    public void user_updates_account_with_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        /*
        PUT Request: 1.Endpoint, 2.Body
         */
        String endpoint="/api/accounts/"+accountId;
        Account account=new Account();
        updatedData=dataTable.asMap(String.class,Object.class);
        account.setAccountType(updatedData.get("accountType").toString());
        account.setBalance(Double.valueOf(updatedData.get("balance").toString()));
        account.setId(Integer.valueOf(accountId));
         response= ApiUtilsBank.putCall(endpoint,account);

    }

    @Then("user validates that account is updated with api get call")
    public void user_validates_that_account_is_updated_with_api_get_call() {
        String endpoint="/api/accounts/"+accountId;
        response=ApiUtilsBank.getCall(endpoint);
        Account responseAccount= response.body().as(Account.class);
        Assert.assertEquals(updatedData.get("accountType"),responseAccount.getAccountType());
        Assert.assertEquals(Double.valueOf(updatedData.get("balance").toString()),responseAccount.getBalance());

    }

    @When("user deletes account with api call")
    public void user_deletes_account_with_api_call() {
        /*
        DELETE Request: 1.Endpoint
         */
        String endpoint="/api/accounts/"+accountId;
        response=ApiUtilsBank.deleteCall(endpoint);

    }

    @Then("user validates that account is deleted with api get call")
    public void user_validates_that_account_is_deleted_with_api_get_call() {
        String endpoint="/api/accounts/"+accountId;
        response=ApiUtilsBank.getCall(endpoint);
        response.then().statusCode(404);
    }

}
