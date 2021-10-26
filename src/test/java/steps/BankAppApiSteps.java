package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.bankpojos.Account;
import utilities.ApiUtilsBank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankAppApiSteps {
    Map<String, Object> accountData;
    Map<String, Object> updatedData;
    Response response;
    String accountId;

    @Given("user creates account with api call with data")
    public void user_creates_account_with_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        /*
        POST call->Request :1.Endpoint  2.Body using POJO objects
         */

        String endpoint = "/api/account";
        Account account = new Account();
        accountData = dataTable.asMap(String.class, Object.class);
        account.setAccountType(accountData.get("accountType").toString());
        account.setBalance(Double.valueOf(accountData.get("balance").toString()));

        response = ApiUtilsBank.postCall(endpoint, account); //POJO-> JSON
        accountId = response.body().jsonPath().getString("id");

    }

    @Then("user validates status code {int}")
    public void user_validates_status_code(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());

    }

    @Then("user validates that account is created with api get call")
    public void user_validates_that_account_is_created_with_api_get_call() {
        /*
        GET Request; 1.Endpoint
         */
        String endpoint = "/api/accounts/" + accountId;
        response = ApiUtilsBank.getCall(endpoint);
        Account responseBody = response.body().as(Account.class);//JSON->POJO
        Assert.assertEquals(accountData.get("AccountType").toString(), responseBody.getAccountType());
        Assert.assertEquals(Double.valueOf(accountData.get("balance").toString()), responseBody.getBalance());

    }

    @When("user updates account with  api call with data")
    public void user_updates_account_with_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        /*
        PUT Request: 1.Endpoint, 2.Body
         */
        String endpoint = "/api/accounts/" + accountId;
        Account account = new Account();
        updatedData = dataTable.asMap(String.class, Object.class);
        account.setAccountType(updatedData.get("accountType").toString());
        account.setBalance(Double.valueOf(updatedData.get("balance").toString()));
        account.setId(Integer.valueOf(accountId));
        response = ApiUtilsBank.putCall(endpoint, account);

    }

    @Then("user validates that account is updated with api get call")
    public void user_validates_that_account_is_updated_with_api_get_call() {
        String endpoint = "/api/accounts/" + accountId;
        response = ApiUtilsBank.getCall(endpoint);
        Account responseAccount = response.body().as(Account.class);
        Assert.assertEquals(updatedData.get("accountType"), responseAccount.getAccountType());
        Assert.assertEquals(Double.valueOf(updatedData.get("balance").toString()), responseAccount.getBalance());

    }

    @When("user deletes account with api call")
    public void user_deletes_account_with_api_call() {
        /*
        DELETE Request: 1.Endpoint
         */
        String endpoint = "/api/accounts/" + accountId;
        response = ApiUtilsBank.deleteCall(endpoint);

    }

    @Then("user validates that account is deleted with api get call")
    public void user_validates_that_account_is_deleted_with_api_get_call() {
        String endpoint = "/api/accounts/" + accountId;
        response = ApiUtilsBank.getCall(endpoint);
        response.then().statusCode(404);
    }

    @Given("user creates multiple accounts with api call with data")
    public void user_creates_multiple_accounts_with_api_call_with_data(DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);

        String endpoint = "/api/account";
        for (int i = 0; i < data.size(); i++) {
            Account accountObject = new Account();
            accountObject.setAccountType(data.get(i).get("accountType").toString());
            accountObject.setBalance(Double.valueOf(data.get(i).get("balance").toString()));
            response = ApiUtilsBank.postCall(endpoint, accountObject);
            response.then().statusCode(201);
        }

    }

    @When("user gets all accounts with api call in {string} order")
    public void user_gets_all_accounts_with_api_call_in_order(String order) {

        String endpoint = "/api/accounts";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("order", order);
        // queryParams.put("limit",10);
        response = ApiUtilsBank.getCall(endpoint, queryParams);

    }

    @Then("user validates that accounts are in {string} order")
    public void user_validates_that_accounts_are_in_order(String order) {

        Account[] accounts = response.body().as(Account[].class); //JSON->POJO Deserialization
        for (int i = 0; i < accounts.length - 1; i++) {
            if (order.equalsIgnoreCase("asc")) {
                Assert.assertTrue(accounts[i].getBalance() <= accounts[i + 1].getBalance());
            } else if (order.equalsIgnoreCase("desc")) {
                Assert.assertTrue(accounts[i].getBalance() >= accounts[i + 1].getBalance());
            }
        }
    }

    @When("user gets all accounts with api call in {int} limit")
    public void user_gets_all_accounts_with_api_call_in_limit(Integer limit) {
        String endpoint = "/api/accounts";
        Map<String, Object> queryParams = new HashMap<>();

         queryParams.put("limit",limit);

        response = ApiUtilsBank.getCall(endpoint, queryParams);
    }

    @Then("user validates that accounts are in {int} limit")
    public void user_validates_that_accounts_are_in_limit(int limit) {
        Account [] accounts=response.body().as(Account[].class);
        Assert.assertEquals(limit,accounts.length);
    }

}
