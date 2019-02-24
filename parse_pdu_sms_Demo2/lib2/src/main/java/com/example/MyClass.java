package com.example;

import org.ajwcc.pduUtils.gsm3040.Pdu;
import org.ajwcc.pduUtils.gsm3040.PduParser;
import org.ajwcc.pduUtils.gsm3040.SmsDeliveryPdu;

public class MyClass {

    public static void main(String[] arg) {
        System.out.print("XXXX");
        String str = "0891683110102105F0240D91685119602268F300007130617161052302E834";
////               String str = SMSTools.getSMSText("+CMGR: 0,,22\n0891683110102105F0240D91685119602268F300007130617161052302E834");
//        String str = SMSTools.getSMSText("0891683110402505F0240BA15150800576F700000111208160302304D4F29C0E");
        Pdu pdu = new PduParser().parsePdu(str);

        String address = pdu.getAddress();
        String text = pdu.getDecodedText();
        String date = ((SmsDeliveryPdu) pdu).getTimestamp().toString();
        String res = String.format("address=%s,text=%s,date=%s", address, text, date);

        System.out.print("reslult: " + res);
    }


}
