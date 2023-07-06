package com.skypro.petsheltersbot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.skypro.petsheltersbot.handlers.AbstractMessagingHandler;
import com.skypro.petsheltersbot.handlers.GetCatHandler;
import com.skypro.petsheltersbot.handlers.GetDogHandler;
import com.skypro.petsheltersbot.handlers.TelegramHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetHandlerTest {

    @Mock
    private static TelegramBot telegramBot;

    @Mock
    private static AbstractMessagingHandler messagingHandler;

    @Mock
    private GetCatHandler getCatHandler;

    @Mock
    private GetDogHandler getDogHandler;


    @Test
    @DisplayName("Testing appliesTo method with callbackQuery update data:/Cats")
    void testAppliesToWithCallbackQueryUpdate() {
        Update update = mock(Update.class);
        CallbackQuery callbackQuery = mock(CallbackQuery.class);

        when(update.callbackQuery()).thenReturn(callbackQuery);
        when(update.callbackQuery().data()).thenReturn("/Menu");

        assertFalse(getCatHandler.appliesTo(update));
    }

    @Test
    @DisplayName("Testing appliesTo method with callbackQuery update data:/Menu")
    void testAppliesToWithCallbackQueryUpdateShouldTrue() {
        Update update = mock(Update.class);
        CallbackQuery callbackQuery = mock(CallbackQuery.class);

        when(update.callbackQuery()).thenReturn(callbackQuery);
        when(update.callbackQuery().data()).thenReturn("/Menu");

        Assertions.assertFalse(getDogHandler.appliesTo(update));
    }

    @Test
    @DisplayName("Testing appliesTo method with callbackQuery = null")
    void testAppliesToWithMessageUpdate() {
        Update update = mock(Update.class);
        Assertions.assertFalse(getCatHandler.appliesTo(update));
    }

    @Test
    @DisplayName("Testing appliesTo method with wrong callbackQuery update")
    void testAppliesToWithWrongCallbackQueryUpdate() {
        Update update = mock(Update.class);
        CallbackQuery callbackQuery = mock(CallbackQuery.class);

        when(update.callbackQuery()).thenReturn(callbackQuery);
        when(update.callbackQuery().data()).thenReturn("/start");

        Assertions.assertFalse(getDogHandler.appliesTo(update));
    }


}