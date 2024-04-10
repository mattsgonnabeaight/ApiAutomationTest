package api;

import api.Model.Root;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserInfoAutoTest {
    private static int id = 10;
    private final int [] maleUserList = UserListAutoTest.createTestUserList(UserListAutoTest.maleParam).idList;
    private int [] femaleUserList = UserListAutoTest.createTestUserList(UserListAutoTest.femaleParam).idList;
    public Root createTestUser(Integer id) {
        RequestSpecification request = RestAssured.given();
        String url = "https://hr-challenge.dev.tapyou.com";
        Response response = request.get(url + "/api/test/user/" + id);
        return response.getBody().as(Root.class);
    }

    @Test
    public void checkUserInfo() {
        assertTrue(createTestUser(id).isSuccess);
    }

    @Test
    public void checkMaleUserInfoResponseErrorCodePositive() {assertEquals(0,createTestUser(id).errorCode);
    }

    @Test
    public void checkUserInfoResponseErrorMessagePositive() {assertNull(createTestUser(id).errorMessage);
    }
}
