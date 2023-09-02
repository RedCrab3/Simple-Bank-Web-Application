package com.luv2code.testdb;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class OTPtest {
  // Find your Account Sid and Token at twilio.com/user/account
  public static final String ACCOUNT_SID = "ACce3b86406d4182f24d29bb7ddc9368cd";
  public static final String AUTH_TOKEN = "312e677c95c6b920632280c1d62d4e2c";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+919627869973"),
        new PhoneNumber("+15342482894"), 
        "3690").create();

    System.out.println(message.getSid());
  }
}