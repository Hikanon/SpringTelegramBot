package com.tgbot.springtelegrambot.service;

import com.tgbot.springtelegrambot.KeyBoard;

import com.tgbot.springtelegrambot.commands.StartCommand;
import com.tgbot.springtelegrambot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Component
public class TelegramBot extends TelegramLongPollingCommandBot {
    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
        register(new StartCommand("start", "Старт"));
    }



    @Override
    public void processNonCommandUpdate(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            if ("Анекдот".equals(update.getMessage().getText())) {
                setAnswer(update.getMessage().getChatId(), Anekdots.getAnekdot(new Random().nextInt(194)));
            } else {
                setAnswer(update.getMessage().getChatId(), "Ты куда жмал?");
            }

        }


    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    private void setAnswer(Long chatId, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        answer.setReplyMarkup(new KeyBoard().getMainMenuKeyBoard());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
