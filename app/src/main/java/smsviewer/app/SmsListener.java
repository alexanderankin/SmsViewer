package smsviewer.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import smsviewer.app.crud_example.Example;
import smsviewer.app.crud_example.ExampleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION;
import static android.provider.Telephony.Sms.Intents.getMessagesFromIntent;

public class SmsListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            List<SmsMessage> smsMessages =
                    Arrays.asList(getMessagesFromIntent(intent));

            try (ExampleRepository exampleRepository =
                         new ExampleRepository(context)) {
                exampleRepository
                        .addAll(smsMessages.stream()
                                .map(this::toExample)
                                .collect(Collectors.toList()));
            }
        }
    }

    private Example toExample(SmsMessage smsMessage) {
        return new Example()
                .setName(smsMessage.getEmailFrom() + ": " +
                        smsMessage.getMessageBody());
    }
}
