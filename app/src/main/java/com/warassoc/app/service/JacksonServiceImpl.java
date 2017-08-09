package com.warassoc.app.service;

import android.text.TextUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 8/9/2017.
 */

public class JacksonServiceImpl implements JacksonService {

    public final static String SERVICE = "JacksonService";
    //private static final Logger logger = LoggerFactory.getLogger(JacksonServiceImpl.class);

    private static final String OBJECT_TO_JSON_FAILURE_MESSAGE = "JacksonService: Fatal Error occured while converting object to json.";
    private static final String JSON_TO_OBJECT_FAILURE_MESSAGE = "JacksonService: Fatal Error occured while converting json to object.";

    /**
     * {@inheritDoc}
     */
    @Override
    public String toJson(Object object) {
        //logger.trace("in JacksonServiceImpl.toJson");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = null;
        try {
            json = ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return message(object, e.getMessage(), OBJECT_TO_JSON_FAILURE_MESSAGE);
        } catch (Exception e) {
            return message(object, e.getMessage(), OBJECT_TO_JSON_FAILURE_MESSAGE);
        }

        //logger.trace("out JacksonServiceImpl.toJson");

        return json;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T toObject(String json, Class<T> clazz) {
        //logger.trace("in JacksonServiceImpl.toObject");

        ObjectMapper mapper = new ObjectMapper();

        T result = null;
        try {
            result = mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            //logger.trace("JacksonServiceImpl.toObject " + message(clazz.getName(), e.getMessage(), JSON_TO_OBJECT_FAILURE_MESSAGE));
            return null;
        } catch (Exception e) {
            //logger.trace("JacksonServiceImpl.toObject " + message(clazz.getName(), e.getMessage(), JSON_TO_OBJECT_FAILURE_MESSAGE));
            return null;
        }

        //logger.trace("out JacksonServiceImpl.toObject");

        return result;

    }

    private static String getClassName(Object object) {
        String response = "UNKNOWN CLASS NAME";
        if (object == null) {
            return response;
        }

        if (object.getClass() != null) {
            response = object.getClass().getName();
        }
        return response;
    }

    private static String message(Object object, String exceptionMessage, String failureMessage) {

        String className = "UNKNOWN CLASS NAME";
        if (object != null) {
            className = getClassName(object);
        }
        if (TextUtils.isEmpty(exceptionMessage)) {
            exceptionMessage = "UNKNOWN EXCEPTION MESSAGE";
        }

        return message(className, exceptionMessage, failureMessage);
    }

    private static String message(String className, String exceptionMessage, String failureMessage) {

        if (TextUtils.isEmpty(className)) {
            className = "UNKNOWN CLASS NAME";
        }
        if (TextUtils.isEmpty(exceptionMessage)) {
            exceptionMessage = "UNKNOWN EXCEPTION MESSAGE";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\n    ");
        sb.append("\"className\"");
        sb.append(" : ");
        sb.append("\"");
        sb.append(className);
        sb.append("\"");
        sb.append(",\n");
        sb.append("\"exception\"");
        sb.append(" : ");
        sb.append("\"");
        sb.append(exceptionMessage);
        sb.append("\"");
        sb.append(",\n");
        sb.append("\"message\"");
        sb.append(" : ");
        sb.append("\"");
        sb.append(failureMessage);
        sb.append("\"");
        sb.append("\n");
        sb.append("}");

        return sb.toString();
    }
}
