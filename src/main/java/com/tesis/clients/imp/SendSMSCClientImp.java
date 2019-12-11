package com.tesis.clients.imp;

import com.mashape.unirest.http.Unirest;
import com.tesis.clients.SendSMSCClient;
import com.tesis.exceptions.ApiException;
import com.tesis.models.SMSRequest;
import com.tesis.models.SMSResponse;
import com.tesis.routes.Router;
import com.tesis.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

import static com.tesis.enums.ErrorCodes.internal_error;
import static com.tesis.enums.ErrorCodes.invalid_data;


public class SendSMSCClientImp implements SendSMSCClient {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);


    @Override
    public void sendSMS(SMSRequest smsRequest) throws ApiException {
        try {

            String url = String.format("https://www.smsc.com.ar/api/0.3/?alias=%s&apikey=%s&cmd=enviar&num=%s&msj=%s",
                    System.getenv("SMSC_ALIAS"),
                    System.getenv("SMSC_API_KEY"),
                    smsRequest.getReceptor(),
                    smsRequest.getMessage());

            String result = Unirest.get(url).asJson().getBody().toString();

            SMSResponse resp = JsonUtils.INSTANCE.GSON().fromJson(result, SMSResponse.class);

            switch (resp.getCode()){
                case "200":
                    logger.info("SMS enviado a " + smsRequest.getReceptor());
                    break;
                case "401":
                    logger.error(String.format("Error al enviar sms. Code: %s. Reason: %s",resp.getCode(), resp.getMessage()));
                    throw new ApiException(internal_error.name(),
                            String.format("ERROR. Reason: %s", resp.getMessage()),
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                case "403":
                    logger.error(String.format("Error al enviar sms. Code: %s. Reason: %s",resp.getCode(), resp.getMessage()));
                    throw new ApiException(invalid_data.name(),
                            String.format("invalid phone number: %s", smsRequest.getReceptor()),
                            HttpServletResponse.SC_NOT_FOUND);
                case "405":
                    logger.error(String.format("Error al enviar sms. Code: %s. Reason: %s",resp.getCode(), resp.getMessage()));
                    throw new ApiException(invalid_data.name(),
                            "no messages available",
                            HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApiException(internal_error.name(), e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void sendAlertSMS(SMSRequest smsRequest, String alertType) throws ApiException {
        String url, result;
        try {

            if (alertType.equals("SPEED"))
                result = Unirest.get("https://www.smsc.com.ar/api/0.3/?alias=" + System.getenv("SMSC_ALIAS") +
                        "&apikey=" + System.getenv("SMSC_API_KEY") +
                        "&cmd=enviar&num=" + smsRequest.getReceptor() +
                        "&msj=ALERTA%20DE%20VELOCIDAD!!%20se%20ha%20sobrepasado%20el%20límite%20de%20la%20alerta%20de%20velocidad.")
                        .asJson().getBody().toString();

//                url = String.format("https://www.smsc.com.ar/api/0.3/?alias=%s&apikey=%s&cmd=enviar&num=%s&msj=%s",
//                        System.getenv("SMSC_ALIAS"),
//                        System.getenv("SMSC_API_KEY"),
//                        smsRequest.getReceptor(),
//                        "ALERTA%20DE%20VELOCIDAD!!%20se%20ha%20sobrepasado%20el%20límite%20de%20la%20alerta%20de%20velocidad.");
            else
                result = Unirest.get("https://www.smsc.com.ar/api/0.3/?alias=" + System.getenv("SMSC_ALIAS") +
                        "&apikey=" + System.getenv("SMSC_API_KEY") +
                        "&cmd=enviar&num=" + smsRequest.getReceptor() +
                        "&msj=ALERTA%20DE%20MOVIMIENTO!!%20se%20ha%20desplazado%20el%20vehiculo%20durante%20una%20alerta.")
                        .asJson().getBody().toString();
//                url = String.format("https://www.smsc.com.ar/api/0.3/?alias=%s&apikey=%s&cmd=enviar&num=%s&msj=%s",
//                        System.getenv("SMSC_ALIAS"),
//                        System.getenv("SMSC_API_KEY"),
//                        smsRequest.getReceptor(),
//                        "ALERTA%20DE%20MOVIMIENTO!!%20se%20ha%20desplazado%20el%20vehiculo%20durante%20una%20alerta.");


//            result = Unirest.get(url).asJson().getBody().toString();



            SMSResponse resp = JsonUtils.INSTANCE.GSON().fromJson(result, SMSResponse.class);

            switch (resp.getCode()){
                case "200":
                    logger.info("SMS enviado a " + smsRequest.getReceptor());
                    break;
                case "401":
                    logger.error(String.format("Error al enviar sms. Code: %s. Reason: %s",resp.getCode(), resp.getMessage()));
                    throw new ApiException(internal_error.name(),
                            String.format("ERROR. Reason: %s", resp.getMessage()),
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                case "403":
                    logger.error(String.format("Error al enviar sms. Code: %s. Reason: %s",resp.getCode(), resp.getMessage()));
                    throw new ApiException(invalid_data.name(),
                            String.format("invalid phone number: %s", smsRequest.getReceptor()),
                            HttpServletResponse.SC_NOT_FOUND);
                case "405":
                    logger.error(String.format("Error al enviar sms. Code: %s. Reason: %s",resp.getCode(), resp.getMessage()));
                    throw new ApiException(invalid_data.name(),
                            "no messages available",
                            HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApiException(internal_error.name(), e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
