package com.paddy.createContacts;

import com.ringcentral.RestClient;
import com.ringcentral.RestException;
import com.ringcentral.definitions.CreateSMSMessage;
import com.ringcentral.definitions.MessageInfo;
import com.ringcentral.definitions.MessageStoreCallerInfoRequest;
import com.ringcentral.definitions.PersonalContactInfo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws InterruptedException {
////        sendSMS();
//        System.out.println("Please enter the number of contacts you want to create: ");
//        Scanner console = new Scanner(System.in);
//        String number = console.next();
//        ArrayList<PersonalContactInfo> contacts = createContact(number);
//        System.out.println("Here are the contacts that were successfully created: ");
//        for (PersonalContactInfo contact : contacts) {
//            System.out.println(contact.firstName + contact.lastName);
//        }
//        try {
//            restClient.revoke();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (RestException e) {
//            e.printStackTrace();
//        }
    }

//    public static void sendSMS() {
//        CreateSMSMessage postParameters = new CreateSMSMessage();
//        postParameters.from = new MessageStoreCallerInfoRequest().phoneNumber("+18582573464");
//        postParameters.to = new MessageStoreCallerInfoRequest[]{new MessageStoreCallerInfoRequest().phoneNumber("+18582573464")};
//        postParameters.text = "Test SMS message from Platform server";
//        MessageInfo messageInfo = null;
//        try {
//            messageInfo = requestAPI().post("/restapi/v1.0/account/~/extension/~/sms", postParameters, MessageInfo.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (RestException e) {
//            e.printStackTrace();
//        }
//        System.out.println(messageInfo.subject);
//
//    }

    protected static RestClient restClient = null;
    public static RestClient requestAPI(String phoneNumber,String ext) {
        if(restClient != null) {
            return restClient;
        }
        restClient = new RestClient("eac8797af1b3502F2CEAAEECAC3Ed378AA7858A386656f28A008b0c638A754B1", "c082702E4ea4DA18c4b1377917778a8aafabCA3Be579B78B66d17C36874b27F4", "https://api-xmnup.lab.nordigy.ru");
        try {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Please enter you E164 phoneNumber: ");
//            String phoneNumber = scanner.next();
//            System.out.println("Please enter you extension: ");
//            String ext = scanner.next();
            restClient.authorize(phoneNumber, ext, "Test!123");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RestException e) {
            e.printStackTrace();
        }
        return restClient;
    }

    public static ArrayList<PersonalContactInfo> createContact(String phoneNumber,String ext,int count) throws InterruptedException, IOException, RestException {
        Thread.sleep(1000);
        PersonalContactInfo parameters = new PersonalContactInfo();
        ArrayList<PersonalContactInfo> list = new ArrayList<PersonalContactInfo>();
        try {
                for (int i = 0; i < count; i++) {
                    //用时间戳来表示号码
                    long timeSec = System.currentTimeMillis() / 1000;
                    String timeStamp = String.format("%10d", timeSec);
                    parameters.firstName = "Test";
                    parameters.lastName = "account" + (i+1);
                    parameters.businessPhone = "+1" + timeStamp;
                    PersonalContactInfo responseInfo = requestAPI(phoneNumber,ext)
                            .post("/restapi/v1.0/account/~/extension/~/address-book/contact",
                                    parameters, PersonalContactInfo.class);
                    list.add(responseInfo);
                }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RestException e) {
            e.printStackTrace();
        }
        return list;
    }
}
