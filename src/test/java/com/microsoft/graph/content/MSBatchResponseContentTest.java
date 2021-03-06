package com.microsoft.graph.content;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MSBatchResponseContentTest {
	
	@Test
	public void testNullMSBatchResponseContent() {
		Response response = null;
		try {
			new MSBatchResponseContent(response);
		}
		catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testValidMSBatchResponseContent() {
		String responsebody = "{\"responses\": [{\"id\": \"1\",\"status\":200,\"headers\" : {\"Cache-Control\":\"no-cache\",\"OData-Version\":\"4.0\",\"Content-Type\":\"application/json;odata.metadata=minimal;odata.streaming=true;IEEE754Compatible=false;charset=utf-8\"},\"body\":{\"@odata.context\":\"https://graph.microsoft.com/v1.0/$metadata#users/$entity\",\"businessPhones\":[\"8006427676\"],\"displayName\":\"MOD Administrator\",\"givenName\":\"MOD\",\"jobTitle\":null,\"mail\":\"admin@M365x751487.OnMicrosoft.com\",\"mobilePhone\":\"425-882-1032\",\"officeLocation\":null,\"preferredLanguage\":\"en-US\",\"surname\":\"Administrator\",\"userPrincipalName\":\"admin@M365x751487.onmicrosoft.com\",\"id\":\"6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c\"}},{\"id\": \"2\",\"status\":200,\"headers\" : {\"Cache-Control\":\"no-store, no-cache\",\"OData-Version\":\"4.0\",\"Content-Type\":\"application/json;odata.metadata=minimal;odata.streaming=true;IEEE754Compatible=false;charset=utf-8\"},\"body\":{\"@odata.context\":\"https://graph.microsoft.com/v1.0/$metadata#drives/$entity\",\"createdDateTime\":\"2019-01-12T09:05:38Z\",\"description\":\"\",\"id\":\"b!nlu9o5I9g0y8gsHXfUM_bPTZ0oM_wVNArHM5R4-VkHLlnxx5SpqHRJledwfICP9f\",\"lastModifiedDateTime\":\"2019-03-06T06:59:04Z\",\"name\":\"OneDrive\",\"webUrl\":\"https://m365x751487-my.sharepoint.com/personal/admin_m365x751487_onmicrosoft_com/Documents\",\"driveType\":\"business\",\"createdBy\":{\"user\":{\"displayName\":\"System Account\"}},\"lastModifiedBy\":{\"user\":{\"displayName\":\"System Account\"}},\"owner\":{\"user\":{\"email\":\"admin@M365x751487.OnMicrosoft.com\",\"id\":\"6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c\",\"displayName\":\"MOD Administrator\"}},\"quota\":{\"deleted\":0,\"remaining\":1099509670098,\"state\":\"normal\",\"total\":1099511627776,\"used\":30324}}},{\"id\": \"3\",\"status\":201,\"headers\" : {\"Location\":\"https://graph.microsoft.com/v1.0/users/6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c/onenote/notebooks/1-94e4376a-a1c1-441a-8b41-af5c86ee39d0\",\"Preference-Applied\":\"odata.include-annotations=*\",\"Cache-Control\":\"no-cache\",\"OData-Version\":\"4.0\",\"Content-Type\":\"application/json;odata.metadata=minimal;odata.streaming=true;IEEE754Compatible=false;charset=utf-8\"},\"body\":{\"@odata.context\":\"https://graph.microsoft.com/v1.0/$metadata#users('6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c')/onenote/notebooks/$entity\",\"id\":\"1-94e4376a-a1c1-441a-8b41-af5c86ee39d0\",\"self\":\"https://graph.microsoft.com/v1.0/users/6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c/onenote/notebooks/1-94e4376a-a1c1-441a-8b41-af5c86ee39d0\",\"createdDateTime\":\"2019-03-06T08:08:09Z\",\"displayName\":\"My Notebook -442293399\",\"lastModifiedDateTime\":\"2019-03-06T08:08:09Z\",\"isDefault\":false,\"userRole\":\"Owner\",\"isShared\":false,\"sectionsUrl\":\"https://graph.microsoft.com/v1.0/users/6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c/onenote/notebooks/1-94e4376a-a1c1-441a-8b41-af5c86ee39d0/sections\",\"sectionGroupsUrl\":\"https://graph.microsoft.com/v1.0/users/6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c/onenote/notebooks/1-94e4376a-a1c1-441a-8b41-af5c86ee39d0/sectionGroups\",\"createdBy\":{\"user\":{\"id\":\"6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c\",\"displayName\":\"MOD Administrator\"}},\"lastModifiedBy\":{\"user\":{\"id\":\"6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c\",\"displayName\":\"MOD Administrator\"}},\"links\":{\"oneNoteClientUrl\":{\"href\":\"onenote:https://m365x751487-my.sharepoint.com/personal/admin_m365x751487_onmicrosoft_com/Documents/Notebooks/My%20Notebook%20-442293399\"},\"oneNoteWebUrl\":{\"href\":\"https://m365x751487-my.sharepoint.com/personal/admin_m365x751487_onmicrosoft_com/Documents/Notebooks/My%20Notebook%20-442293399\"}}}}]}";
		String requestbody = "{\"requests\":[{\"method\":\"GET\",\"dependsOn\":[],\"id\":\"1\",\"url\":\"me\"},{\"method\":\"GET\",\"dependsOn\":[],\"id\":\"2\",\"url\":\"me\\/drive\"},{\"headers\":{\"content-type\":\"application\\/json\"},\"method\":\"POST\",\"dependsOn\":[],\"id\":\"3\",\"body\":{\"displayName\":\"My Notebook -1263732088\"},\"url\":\"me\\/onenote\\/notebooks\"}]}";
		Response responsedata = TestResponse(responsebody, requestbody);
		MSBatchResponseContent batchresponse = new MSBatchResponseContent(responsedata);
		assertTrue(batchresponse.getResponses() != null);
	}
	
	@Test
	public void testInvalidMSBatchResponseContentWithEmptyResponse() {
		String responsebody = "{\"responses\": [] }";
		String requestbody = "{\"requests\":[]}";
		Response responsedata = TestResponse(responsebody, requestbody);
		MSBatchResponseContent batchresponse = new MSBatchResponseContent(responsedata);
		assertTrue(batchresponse.getResponseById("1") == null);
	}
	
	@Test
	public void testInvalidMSBatchResponseContentWithNullResponseString() {
		try{
			new MSBatchResponseContent(null);
		}catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	@Test
	public void testInvalidMSBatchResponseContentWithMalformedResponse() {
		String invalidResponsebody = "{responses: [] }";
		String requestbody = "{requests:[]}";
		Response invalidResponsedata = TestResponse(invalidResponsebody, requestbody);
		MSBatchResponseContent batchresponse = new MSBatchResponseContent(invalidResponsedata);
		assertTrue(batchresponse.getResponses() == null);
	}
	
	@Test
	public void testGetMSBatchResponseContentByID() throws IOException {
		String responsebody = "{\"responses\": [{\"id\": \"1\",\"status\":200,\"headers\" : {\"Cache-Control\":\"no-cache\",\"OData-Version\":\"4.0\",\"Content-Type\":\"application/json;odata.metadata=minimal;odata.streaming=true;IEEE754Compatible=false;charset=utf-8\"},\"body\":{\"@odata.context\":\"https://graph.microsoft.com/v1.0/$metadata#users/$entity\",\"businessPhones\":[\"8006427676\"],\"displayName\":\"MOD Administrator\",\"givenName\":\"MOD\",\"jobTitle\":null,\"mail\":\"admin@M365x751487.OnMicrosoft.com\",\"mobilePhone\":\"425-882-1032\",\"officeLocation\":null,\"preferredLanguage\":\"en-US\",\"surname\":\"Administrator\",\"userPrincipalName\":\"admin@M365x751487.onmicrosoft.com\",\"id\":\"6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c\"}},{\"id\": \"2\",\"status\":200,\"headers\" : {\"Cache-Control\":\"no-store, no-cache\",\"OData-Version\":\"4.0\",\"Content-Type\":\"application/json;odata.metadata=minimal;odata.streaming=true;IEEE754Compatible=false;charset=utf-8\"},\"body\":{\"@odata.context\":\"https://graph.microsoft.com/v1.0/$metadata#drives/$entity\",\"createdDateTime\":\"2019-01-12T09:05:38Z\",\"description\":\"\",\"id\":\"b!nlu9o5I9g0y8gsHXfUM_bPTZ0oM_wVNArHM5R4-VkHLlnxx5SpqHRJledwfICP9f\",\"lastModifiedDateTime\":\"2019-03-06T06:59:04Z\",\"name\":\"OneDrive\",\"webUrl\":\"https://m365x751487-my.sharepoint.com/personal/admin_m365x751487_onmicrosoft_com/Documents\",\"driveType\":\"business\",\"createdBy\":{\"user\":{\"displayName\":\"System Account\"}},\"lastModifiedBy\":{\"user\":{\"displayName\":\"System Account\"}},\"owner\":{\"user\":{\"email\":\"admin@M365x751487.OnMicrosoft.com\",\"id\":\"6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c\",\"displayName\":\"MOD Administrator\"}},\"quota\":{\"deleted\":0,\"remaining\":1099509670098,\"state\":\"normal\",\"total\":1099511627776,\"used\":30324}}},{\"id\": \"3\",\"status\":201,\"headers\" : {\"Location\":\"https://graph.microsoft.com/v1.0/users/6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c/onenote/notebooks/1-94e4376a-a1c1-441a-8b41-af5c86ee39d0\",\"Preference-Applied\":\"odata.include-annotations=*\",\"Cache-Control\":\"no-cache\",\"OData-Version\":\"4.0\",\"Content-Type\":\"application/json;odata.metadata=minimal;odata.streaming=true;IEEE754Compatible=false;charset=utf-8\"},\"body\":{\"@odata.context\":\"https://graph.microsoft.com/v1.0/$metadata#users('6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c')/onenote/notebooks/$entity\",\"id\":\"1-94e4376a-a1c1-441a-8b41-af5c86ee39d0\",\"self\":\"https://graph.microsoft.com/v1.0/users/6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c/onenote/notebooks/1-94e4376a-a1c1-441a-8b41-af5c86ee39d0\",\"createdDateTime\":\"2019-03-06T08:08:09Z\",\"displayName\":\"My Notebook -442293399\",\"lastModifiedDateTime\":\"2019-03-06T08:08:09Z\",\"isDefault\":false,\"userRole\":\"Owner\",\"isShared\":false,\"sectionsUrl\":\"https://graph.microsoft.com/v1.0/users/6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c/onenote/notebooks/1-94e4376a-a1c1-441a-8b41-af5c86ee39d0/sections\",\"sectionGroupsUrl\":\"https://graph.microsoft.com/v1.0/users/6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c/onenote/notebooks/1-94e4376a-a1c1-441a-8b41-af5c86ee39d0/sectionGroups\",\"createdBy\":{\"user\":{\"id\":\"6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c\",\"displayName\":\"MOD Administrator\"}},\"lastModifiedBy\":{\"user\":{\"id\":\"6b4fa8ea-7e6e-486e-a8f4-d00a5b23488c\",\"displayName\":\"MOD Administrator\"}},\"links\":{\"oneNoteClientUrl\":{\"href\":\"onenote:https://m365x751487-my.sharepoint.com/personal/admin_m365x751487_onmicrosoft_com/Documents/Notebooks/My%20Notebook%20-442293399\"},\"oneNoteWebUrl\":{\"href\":\"https://m365x751487-my.sharepoint.com/personal/admin_m365x751487_onmicrosoft_com/Documents/Notebooks/My%20Notebook%20-442293399\"}}}}]}";
		String requestbody = "{\"requests\":[{\"method\":\"GET\",\"dependsOn\":[],\"id\":\"1\",\"url\":\"me\"},{\"method\":\"GET\",\"dependsOn\":[],\"id\":\"2\",\"url\":\"me\\/drive\"},{\"headers\":{\"content-type\":\"application\\/json\"},\"method\":\"POST\",\"dependsOn\":[],\"id\":\"3\",\"body\":{\"displayName\":\"My Notebook -1263732088\"},\"url\":\"me\\/onenote\\/notebooks\"}]}";
		Response responsedata = TestResponse(responsebody,requestbody);
		MSBatchResponseContent batchresponse = new MSBatchResponseContent(responsedata);
		Response response = batchresponse.getResponseById("1");
		assertTrue(response != null);
	}
	
	private Response TestResponse(String responsebody, String requestbody) {
		Response.Builder builder = new Response.Builder();
		builder.body(ResponseBody.create(MediaType.parse("application/json"), responsebody));
		Request request = new Request.Builder().url("https://graph.microsoft.com/").method("POST", RequestBody.create(MediaType.parse("application/json"), requestbody)).build();
		builder.request(request);
		builder.protocol(Protocol.HTTP_1_0);
		builder.code(200);
		builder.message("test message");
		return builder.build();
	}
}
