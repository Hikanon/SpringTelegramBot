package com.tgbot.springtelegrambot.service;

import com.tgbot.springtelegrambot.KeyBoard;
import com.tgbot.springtelegrambot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (message) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMessage(chatId, "Ты куда жмал?");
                    break;
            }
        }
    }

    private void startCommandReceived(long chatId, String name){
        String answer = "Hi, " + name + ", hehe)";
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String text){
        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        message.setReplyMarkup(new KeyBoard().getMainMenyKeyBoard());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
