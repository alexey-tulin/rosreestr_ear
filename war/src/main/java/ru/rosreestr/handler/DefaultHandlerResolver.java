package ru.rosreestr.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rosreestr.handler.logger.IsurLoggerHandler;

import javax.annotation.PostConstruct;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Резолвер обработчиков для сервисов
 */
@SuppressWarnings("rawtypes")
@Component
public class DefaultHandlerResolver implements HandlerResolver {

    @Autowired
    IsurLoggerHandler loggerHandler;

    @PostConstruct
    protected void init() {
        handlerList = new ArrayList<>();
        handlerList.add(loggerHandler);
    }

    private List<Handler> handlerList;

    public List<Handler> getHandlerChain(PortInfo portInfo) {
        return handlerList;
    }

    public void setHandlerList(List<Handler> handlerList) {
        this.handlerList = handlerList;
    }
}