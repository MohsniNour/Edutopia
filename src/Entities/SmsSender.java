/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import static Entities.SmsSender.ACCOUNT_SID;
import static Entities.SmsSender.*;

import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.net.URI;
import java.util.Arrays;

/**
 *
 * @author ADMIN
 */
public class SmsSender {

    /**
     *
     * @author LENOVO
     */
    public static final String ACCOUNT_SID
            = "AC9fca576beefd8208037bf04db9b0f366";
    public static final String AUTH_TOKEN
            = "50f7450f3746f6c51b75fa733243ee90";

    public void send(String s, String x) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber("+21658720616"), // to
                        new PhoneNumber("+17542128962"), // from
                        "" + s)
                .create();
        System.out.println("aslema");
        System.out.println(message.getSid());

    }
}
