package com.tgbot.springtelegrambot.commands;


import com.tgbot.springtelegrambot.KeyBoard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StartCommand extends ServiceCommand {


    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        new SendMessage().setReplyMarkup(new KeyBoard().getMainMenuKeyBoard());
        sendAnswer(absSender, chat.getId(), "Даров, в благородства с тобой играть не буду, хочешь анектод?\n" +
                " Тогда жми на кнопку\n" +
                "Если кнопка \"Анекдот\" не появилась напиши сам слово \"Анекдот\"");
    }
}
