package api;

import api.Model.UserList;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import static org.junit.Assert.*;



public class UserListAutoTest {
    protected static final String maleParam = "male";
    protected static final String femaleParam = "female";

    public static UserList createTestUserList(String gender) {
       RequestSpecification request = RestAssured.given();
       String url = "https://hr-challenge.dev.tapyou.com";
       Response response = request.get(url + "/api/test/users?gender=" + gender);
       return response.getBody().as(UserList.class);
    }

    @Test
    public void checkMaleUserListResponseSuccess() {
       assertTrue(createTestUserList(maleParam).isSuccess);
    }

    @Test
    public void checkMaleUserListResponseErrorCodePositive() {assertEquals(0,createTestUserList(maleParam).errorCode); }

    @Test
    public void checkMaleUserListResponseErrorMessagePositive() {assertNull(createTestUserList(maleParam).errorMessage); }

    @Test
    public void checkMaleUserListResponseArrayNotEmpty() {
        assertTrue(createTestUserList(maleParam).idList.length!=0);
    }

    @Test
    public void checkFemaleUserListResponseSuccess() {
        assertTrue(createTestUserList(femaleParam).isSuccess);
    }

    @Test
    public void checkFemaleUserListResponseErrorCodePositive() { assertEquals(0,createTestUserList(femaleParam).errorCode); }

    @Test
    public void checkFemaleUserListResponseErrorMessagePositive() { assertNull(createTestUserList(femaleParam).errorMessage); }

    @Test
    public void checkFemaleUserListResponseArrayNotEmpty() { assertTrue(createTestUserList(femaleParam).idList.length!=0); }
}
