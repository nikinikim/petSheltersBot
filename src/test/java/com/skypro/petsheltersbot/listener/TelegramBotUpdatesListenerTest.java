package com.skypro.petsheltersbot.listener;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.petsheltersbot.handlers.StartHandler;
import com.skypro.petsheltersbot.handlers.TelegramHandler;
import com.skypro.petsheltersbot.handlers.TextHandler;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.reflect.Whitebox;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TelegramBotUpdatesListenerTest {

    @Mock
    private TelegramBot telegramBot;

    @InjectMocks
    private TelegramBotUpdatesListener telegramBotUpdatesListener;

    @Test
    public void TelegramBotUpdatesListenerTest() throws Exception {
        String json = Files.readString(Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.json").toURI()));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        List<TelegramHandler> handlers = new ArrayList<>();
        handlers.add(new StartHandler(telegramBot));
        handlers.add(new TextHandler(telegramBot));
        JSONArray jsonArr = new JSONArray(json);
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            Update update = BotUtils.fromJson(jsonObj.toString(), Update.class);

            Whitebox.setInternalState(telegramBotUpdatesListener, "handlers", handlers);
            telegramBotUpdatesListener.process(Collections.singletonList(update));
            Mockito.verify(telegramBot, atLeastOnce()).execute(argumentCaptor.capture());
            SendMessage actual = argumentCaptor.getValue();

            Assertions.assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.message().chat().id());
            Assertions.assertThat(actual.getParameters().get("text")).isIn("""
                            Привет, null! Выберите приют, нажав на кнопку ниже:"""
                    , "Нажмите кнопку /start и выберите приют ('Приют для кошек' или 'Приют для собак')"
                    , "Выберите направление, нажав на кнопку ниже:"
            );
        }
    }
}