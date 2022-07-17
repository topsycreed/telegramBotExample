package Core;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import static Data.Messages.*;
import static Data.Triggers.*;

public class Bot {
    private final TelegramBot bot = new TelegramBot(System.getenv("BOT_TOKEN"));

    public void startBot() {
        bot.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void process(Update update) {
        Message message = update.message();

        SendMessage botReturnMessage = null;

        if (message != null) {
            long chatId = message.chat().id();
            String messageText = message.text();
            if (isDirectQuestion(messageText)) {
                botReturnMessage = new SendMessage(chatId, SIMPLE);
            }
            if (isMen(messageText) && !isPositive(messageText)) {
                botReturnMessage = new SendMessage(chatId, MENS);
            }
            if (isFact(messageText)) {
                botReturnMessage = new SendMessage(chatId, FACT);
            }
            if (isBot(messageText)) {
                botReturnMessage = new SendMessage(chatId, BOT);
            }
        }

        if (botReturnMessage != null) {
            bot.execute(botReturnMessage);
        }
    }
}
