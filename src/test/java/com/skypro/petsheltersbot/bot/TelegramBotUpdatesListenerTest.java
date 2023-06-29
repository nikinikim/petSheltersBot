//package com.skypro.petsheltersbot.bot;
//
//import com.skypro.petsheltersbot.listener.TelegramBotUpdatesListener;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.Collections;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//
//@ExtendWith(MockitoExtension.class)
//public class TelegramBotUpdatesListenerTest {
//
//    @Mock
//    private TelegramBot telegramBot;
//
//    @Mock
//    private DogUserService dogUserService;
//    @InjectMocks
//    private TelegramBotUpdatesListener telegramBotUpdatesListener;
//
//   @Test
//    public void getDogUserHandlerTest() throws URISyntaxException, IOException {
//        String json = Files.readString(Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.json").toURI()));
//        Update update = BotUtils.fromJson(json.replace("%text%", "/Dogs"), Update.class);
//
//        telegramBotUpdatesListener.process(Collections.singletonList(update));
//
//        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
//        Mockito.verify(telegramBot).execute(argumentCaptor.capture());
//        SendMessage actual = argumentCaptor.getValue();
//
//       Assertions.assertThat()
//    }
//
//
//}
