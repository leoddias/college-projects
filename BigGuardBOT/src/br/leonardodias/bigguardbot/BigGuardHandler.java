package br.leonardodias.bigguardbot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Platform;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BigGuardHandler extends TelegramLongPollingBot {

    private String chatID;
    private boolean started = false;
    private FXMLDocumentController controller;

    public BigGuardHandler(FXMLDocumentController controller) {
        this.controller = controller;
    }

    @Override
    public String getBotToken() {
        return "453545090:AAHwnBOnFCMz-9DhmGBvoLZkZRFwBgPpjCA";
    }

    public void onUpdateReceived(Update update) {

        //check if have some message recivied
        if (update.hasMessage()) {
            // got a message in variable "message"
            Message message = update.getMessage();
            //check if the message has text. it can have text/location/photo/document/etc
            if (message.hasText()) {
                //create a object that contains the information to send the message back
                SendMessage sendMessageRequest = new SendMessage();
                SendPhoto sendPhotoRequest = new SendPhoto();
                chatID = message.getChatId().toString();
                sendMessageRequest.setChatId(message.getChatId().toString());
                /*sendMessageRequest.setText(message.getFrom().getFirstName() + " "
                        + message.getFrom().getLastName() + ", você disse: " + message.getText());*/
                String mensagem[] = message.getText().split(" ");
                System.out.println("LOG - CHAT ID: " + chatID + " Usuario: " + message.getFrom().getFirstName() + message.getFrom().getLastName() +" Mensagem " + message.getText());

                switch (mensagem[0]) {
                    case "/help":
                        sendMessageRequest.setText("/start  - Starts reading the webcam;\n"
                                + "/photo  - Take a photo from webcam;\n"
                                + "/record - Start to recoding from webcam;\n"
                                + "/detect start/stop -> Start or stop people detection;");
                        break;
                    case "/start":
                        if (!started) {
                            sendMessageRequest.setText("Olá " + message.getFrom().getFirstName() + " "
                                    + message.getFrom().getLastName() + " iniciando...");
                            Platform.runLater(() -> {
                                controller.startCommand();
                            });
                            started = true;
                        }
                        break;
                    case "/stop":
                        started=false;
                        Platform.runLater(() -> {
                                controller.stopCommand();
                            });
                        break;
                            

                    case "/photo":
                        if (started) {
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
                            Date date = new Date();
                            String filename = dateFormat.format(date) + message.getFrom().getFirstName();
                            Platform.runLater(() -> {
                                controller.photo(filename);
                            });
                            sendMessageRequest.setText("Foto Salva como: " + filename + ".png");
                        }
                        break;

                    case "/record":
                        if (started) {
                            Platform.runLater(() -> {
                                controller.record(true);
                            });
                            sendMessageRequest.setText("Gravando...");
                        }
                        break;

                    case "/detect":
                        if (started) {
                            if (mensagem[1].equals("start")) {
                                sendMessageRequest.setText("Monitoramento iniciado");
                                Platform.runLater(() -> {
                                    controller.setDetect(true);
                                });
                            }
                            if (mensagem[1].equals("stop")) {
                                sendMessageRequest.setText("Monitoramento parado");
                                Platform.runLater(() -> {
                                    controller.setDetect(false);
                                });
                            }
                        }
                        break;
                        
                    default:
                        sendMessageRequest.setText("Comando não reconhecido");
                        break;
                }
                mensagem = null;
                try {
                    sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                } catch (TelegramApiException e) {
                    //do some error handling
                }//end catch()
            }//end if()
        }//end  if()
    }

    public String getBotUsername() {
        return "BigGuardBot";
    }

}
