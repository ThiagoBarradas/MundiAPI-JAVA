package com.mundipagg.api;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mundipagg.api.exceptions.APIException;

public class APIHelper {
    /* used for async execution of API calls using a thread pool */
    private static ExecutorService scheduler = null;
    private static final Object syncRoot = new Object();
  
    /**
     * Singleton access to the threadpool scheduler
     */
    public static ExecutorService getScheduler() {
        if (null == scheduler) {
            synchronized(syncRoot) {
                if (null == scheduler) {
                    scheduler = Executors.newCachedThreadPool();
                }
            }
        }
        return scheduler;
    }

    /**
     * Shutdown all the threads
     */
    public static void shutdown() {
        if(null != scheduler) {
            scheduler.shutdown();
        }
    }

    /* used for deserialization of json data */
    public static ObjectMapper mapper = new ObjectMapper()
    {
        private static final long serialVersionUID = -174113593500315394L;
        {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    };

    /**
     * Get a JsonSerializer instance from the provided annotation.
     * @param  serializerAnnotation The Annotation containing information about the serializer
     * @return The JsonSerializer instance of the required type
     */
    private static JsonSerializer getSerializer(final JsonSerialize serializerAnnotation) {
        try {
            return serializerAnnotation.using().getDeclaredConstructor().newInstance();
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JSON Serialization of a given object.
     * 
     * @param obj The object to serialize into JSON
     * @return The serialized Json string representation of the given object
     */
    public static String serialize(final Object obj) throws JsonProcessingException {
        if (null == obj)
            return null;

        return mapper.writeValueAsString(obj);
    }

    /**
     * JSON Serialization of a given object using a specified JsonSerializer.
     * 
     * @param obj        The object to serialize into JSON
     * @param serializer The instance of JsonSerializer to use
     * @return The serialized Json string representation of the given object
     */
    public static String serialize(final Object obj, final JsonSerializer serializer) throws JsonProcessingException {
        if (null == obj || null == serializer)
            return null;

        if (obj.getClass().getName().equals("java.util.ArrayList")) // need to find the generic type if it's an
                                                                    // ArrayList
        {
            final Class<? extends Object> cls = ((ArrayList) obj).get(0).getClass();

            return new ObjectMapper() {
                private static final long serialVersionUID = -1639089569991988232L;
                {
                    final SimpleModule module = new SimpleModule();
                    module.addSerializer(cls, serializer);
                    this.registerModule(module);
                }
            }.writeValueAsString(obj);
        } else {
            final Class<? extends Object> cls = obj.getClass();

            return new ObjectMapper() {
                private static final long serialVersionUID = -1639089569991988232L;
                {
                    final SimpleModule module = new SimpleModule();
                    module.addSerializer(cls, serializer);
                    this.registerModule(module);
                }
            }.writeValueAsString(obj);
        }
    }

    /**
     * JSON Deserialization of the given json string.
     * 
     * @param json The json string to deserialize
     * @param <T>  The type of the object to deserialize into
     * @return The deserialized object
     */
    public static <T extends Object> T deserialize(final String json, final TypeReference<T> typeReference)
            throws IOException {
        if (isNullOrWhiteSpace(json))
            return null;

        return mapper.readValue(json, typeReference);
    }

    /**
     * JSON Deserialization of the given json string using a specified
     * JsonDerializer.
     * 
     * @param json         The json string to deserialize
     * @param <T>          The type of the object to deserialize into
     * @param cls          The class to attach the deserializer to
     * @param deserializer The deserializer to use
     * @return The deserialized object
     */
    public static <T extends Object> List<T> deserialize(final String json, final TypeReference<List<T>> typeReference,
            final Class<T> cls, final JsonDeserializer<T> deserializer) throws IOException {
        if (isNullOrWhiteSpace(json))
            return null;

        return new ObjectMapper() {
            private static final long serialVersionUID = -1639089569991988232L;

            {
                final SimpleModule module = new SimpleModule();
                module.addDeserializer(cls, deserializer);
                this.registerModule(module);
            }
        }.readValue(json, typeReference);
    }

    /**
     * JSON Deserialization of the given json string.
     * 
     * @param jParser The json parser for reading json to deserialize
     * @param <T>     The type of the object to deserialize into
     * @return The deserialized object
     */
    public static <T extends Object> T deserialize(final String json, final Class<T> typeReference) throws IOException {
        if (isNullOrWhiteSpace(json))
            return null;

        return mapper.readValue(json, typeReference);
    }

    /**
     * Populates an object of an APIException subclass with the required properties.
     * 
     * @param json           The json string to deserialize
     * @param <APIException> The object to populate.
     */
    public static void populate(final String json, final APIException obj) throws IOException {
        if (!isNullOrWhiteSpace(json))
            mapper.readerForUpdating(obj).readValue(json);
        ;
    }

    /**
     * JSON Deserialization of the given json string.
     * 
     * @param json The json string to deserialize
     * @return The deserialized json as a Map
     */
    public static LinkedHashMap<String, Object> deserialize(final String json) throws IOException {
        if (isNullOrWhiteSpace(json))
            return null;

        final TypeReference<LinkedHashMap<String, Object>> typeRef = new TypeReference<LinkedHashMap<String, Object>>() {
        };
        return deserialize(json, typeRef);
    }

    /**
     * Replaces template parameters in the given url
     * 
     * @param queryBuilder The query string builder to replace the template
     *                     parameters
     * @param parameters   The parameters to replace in the url
     */
    public static void appendUrlWithTemplateParameters(final StringBuilder queryBuilder,
            final Map<String, Object> parameters) {
        // perform parameter validation
        if (null == queryBuilder)
            throw new IllegalArgumentException("Given value for parameter \"queryBuilder\" is invalid.");

        if (null == parameters)
            return;

        // iterate and append parameters
        for (final Map.Entry<String, Object> pair : parameters.entrySet()) {
            String replaceValue = "";

            // load element value as string
            if (null == pair.getValue())
                replaceValue = "";
            else if (pair.getValue() instanceof Collection<?>)
                replaceValue = flattenCollection("", (Collection<?>) pair.getValue(), "%s%s%s", '/');
            else
                replaceValue = tryUrlEncode(pair.getValue().toString());

            // find the template parameter and replace it with its value
            replaceAll(queryBuilder, "{" + pair.getKey() + "}", replaceValue);
        }
    }

    /**
     * Appends the given set of parameters to the given query string
     * 
     * @param queryBuilder The query url string to append the parameters
     * @param parameters   The parameters to append
     */
    public static void appendUrlWithQueryParameters(final StringBuilder queryBuilder,
            final Map<String, Object> parameters) {
        // perform parameter validation
        if (null == queryBuilder)
            throw new IllegalArgumentException("Given value for parameter \"queryBuilder\" is invalid.");

        if (null == parameters)
            return;

        // does the query string already has parameters
        final boolean hasParams = queryBuilder.indexOf("?") > 0;
        queryBuilder.append(hasParams ? '&' : '?');

        encodeObjectAsQueryString("", parameters, queryBuilder);
    }

    /**
     * Validates if the string is null, empty or whitespace
     * 
     * @param s The string to validate
     * @return The result of validation
     */
    public static boolean isNullOrWhiteSpace(final String s) {
        if (s == null)
            return true;

        final int length = s.length();
        if (length > 0) {
            for (int start = 0, middle = length / 2, end = length - 1; start <= middle; start++, end--) {
                if (s.charAt(start) > ' ' || s.charAt(end) > ' ') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Replaces all occurrences of the given string in the string builder
     * 
     * @param stringBuilder The string builder to update with replaced strings
     * @param toReplace     The string to replace in the string builder
     * @param replaceWith   The string to replace with
     */
    public static void replaceAll(final StringBuilder stringBuilder, final String toReplace, final String replaceWith) {
        int index = stringBuilder.indexOf(toReplace);

        while (index != -1) {
            stringBuilder.replace(index, index + toReplace.length(), replaceWith);
            index += replaceWith.length(); // Move to the end of the replacement
            index = stringBuilder.indexOf(toReplace, index);
        }
    }

    /**
     * Removes null values from the given map
     */
    public static void removeNullValues(final Map<String, ?> map) {
        if (map == null)
            return;
        map.values().removeAll(Collections.singleton(null));
    }

    /**
     * Validates and processes the given Url
     * 
     * @param url The given Url to process
     * @return Pre-process Url as string
     */
    public static String cleanUrl(final StringBuilder url) {
        // ensure that the urls are absolute
        final Pattern pattern = Pattern.compile("^(https?://[^/]+)");
        final Matcher matcher = pattern.matcher(url);
        if (!matcher.find())
            throw new IllegalArgumentException("Invalid Url format.");

        // get the http protocol match
        final String protocol = matcher.group(1);

        // remove redundant forward slashes
        String query = url.substring(protocol.length());
        query = query.replaceAll("//+", "/");

        // return process url
        return protocol.concat(query);
    }

    /**
     * Prepares Array style form fields from a given array of values
     * 
     * @param value Value for the form fields
     * @return Dictionary of form fields created from array elements
     */
    public static List<SimpleEntry<String, Object>> prepareFormFields(final Object value) {
        final List<SimpleEntry<String, Object>> formFields = new ArrayList<SimpleEntry<String, Object>>();
        if (value != null) {
            try {
                objectToList("", value, formFields, new HashSet<Integer>());
            } catch (final Exception ex) {
            }
        }
        return formFields;
    }

    /**
     * Encodes a given object to url encoded string
     * 
     * @param name
     * @param obj
     * @param objBuilder
     */
    private static void encodeObjectAsQueryString(final String name, final Object obj, final StringBuilder objBuilder) {
        try {
            if (obj == null)
                return;

            final List<SimpleEntry<String, Object>> objectList = new ArrayList<SimpleEntry<String, Object>>();
            objectToList(name, obj, objectList, new HashSet<Integer>());
            boolean hasParam = false;

            final List<String> arrays = new ArrayList<String>();

            for (final SimpleEntry<String, Object> pair : objectList) {
                String paramKeyValPair;
                final String accessor = pair.getKey();
                // ignore nulls
                final Object value = pair.getValue();
                if (value == null)
                    continue;

                hasParam = true;
                // load element value as string
                paramKeyValPair = String.format("%s=%s&", accessor, tryUrlEncode(value.toString()));
                objBuilder.append(paramKeyValPair);

            }

            // remove the last &
            if (hasParam) {
                objBuilder.setLength(objBuilder.length() - 1);
            }
        } catch (final Exception ex) {
        }
    }

    /**
     * Used for flattening a collection of objects into a string
     * 
     * @param array     Array of elements to flatten
     * @param fmt       Format string to use for array flattening
     * @param separator Separator to use for string concat
     * @return Representative string made up of array elements
     */
    private static String flattenCollection(final String elemName, final Collection<?> array, final String fmt,
            final char separator) {
        final StringBuilder builder = new StringBuilder();

        // append all elements in the array into a string
        for (final Object element : array) {
            String elemValue = null;

            // replace null values with empty string to maintain index order
            if (null == element) {
                elemValue = "";
            } else {
                elemValue = element.toString();
            }
            elemValue = tryUrlEncode(elemValue);
            builder.append(String.format(fmt, elemName, elemValue, separator));
        }

        // remove the last separator, if appended
        if ((builder.length() > 1) && (builder.charAt(builder.length() - 1) == separator))
            builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    /**
     * Tries Url encode using UTF-8
     * 
     * @param value The value to url encode
     * @return The encoded url
     */
    private static String tryUrlEncode(final String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (final Exception ex) {
            return value;
        }
    }

    /**
     * Converts a given object to a form encoded map
     * 
     * @param objName    Name of the object
     * @param obj        The object to convert into a map
     * @param objectList The object list to populate
     * @param processed  List of objects hashCodes that are already parsed
     * @throws InvalidObjectException
     */
    private static void objectToList(final String objName, final Object obj,
            final List<SimpleEntry<String, Object>> objectList, final HashSet<Integer> processed)
            throws InvalidObjectException {
        // null values need not to be processed
        if (obj == null)
            return;

        // wrapper types are autoboxed, so reference checking is not needed
        if (!isWrapperType(obj.getClass())) {
            // avoid infinite recursion
            if (processed.contains(objName.hashCode()))
                return;
            processed.add(objName.hashCode());
        }

        // process arrays
        if (obj instanceof Collection<?>) {
            // process array
            if ((objName == null) || (objName.isEmpty()))
                throw new InvalidObjectException("Object name cannot be empty");

            final Collection<?> array = (Collection<?>) obj;
            // append all elements in the array into a string
            int index = 0;
            for (final Object element : array) {
                // load key value pair
                final String key = String.format("%s[%d]", objName, index++);
                loadKeyValuePairForEncoding(key, element, objectList, processed);
            }
        } else if (obj.getClass().isArray()) {
            // process array
            if ((objName == null) || (objName.isEmpty()))
                throw new InvalidObjectException("Object name cannot be empty");

            final Object[] array = (Object[]) obj;
            // append all elements in the array into a string
            int index = 0;
            for (final Object element : array) {
                // load key value pair
                final String key = String.format("%s[%d]", objName, index++);
                loadKeyValuePairForEncoding(key, element, objectList, processed);
            }
        } else if (obj instanceof Map) {
            // process map
            final Map<?, ?> map = (Map<?, ?>) obj;
            // append all elements in the array into a string
            for (final Map.Entry<?, ?> pair : map.entrySet()) {
                final String attribName = pair.getKey().toString();
                String key = attribName;
                if ((objName != null) && (!objName.isEmpty())) {
                    key = String.format("%s[%s]", objName, attribName);
                }
                loadKeyValuePairForEncoding(key, pair.getValue(), objectList, processed);
            }
        } else if (obj instanceof UUID) {
            final String key = objName;
            final String value = obj.toString();
            // UUIDs can be converted to string
            loadKeyValuePairForEncoding(key, value, objectList, processed);
        } else {
            // process objects
            // invoke getter methods
            final Method[] methods = obj.getClass().getMethods();
            for (final Method method : methods) {
                // is a getter?
                if ((method.getParameterTypes().length != 0) || (!method.getName().startsWith("get")))
                    continue;

                // get json attribute name
                final Annotation getterAnnotation = method.getAnnotation(JsonGetter.class);
                if (getterAnnotation == null)
                    continue;

                // load key name
                final String attribName = ((JsonGetter) getterAnnotation).value();
                String key = attribName;
                if ((objName != null) && (!objName.isEmpty())) {
                    key = String.format("%s[%s]", objName, attribName);
                }

                try {
                    // load key value pair
                    final Object value = method.invoke(obj);
                    final JsonSerialize serializerAnnotation = method.getAnnotation(JsonSerialize.class);
                    if (serializerAnnotation != null)
                        loadKeyValuePairForEncoding(key, value, objectList, processed, serializerAnnotation);
                    else
                        loadKeyValuePairForEncoding(key, value, objectList, processed);
                } catch (final Exception ex) {
                }
            }
            // load fields
            final Field[] fields = obj.getClass().getFields();
            for (final Field field : fields) {
                // load key name
                final String attribName = field.getName();
                String key = attribName;
                if ((objName != null) && (!objName.isEmpty())) {
                    key = String.format("%s[%s]", objName, attribName);
                }

                try {
                    // load key value pair
                    final Object value = field.get(obj);
                    loadKeyValuePairForEncoding(key, value, objectList, processed);
                } catch (final Exception ex) {
                }
            }
        }
    }

    /**
     * While processing objects to map, decides whether to perform recursion or load
     * value
     * 
     * @param key        The key to used for creating key value pair
     * @param value      The value to process against the given key
     * @param objectList The object list to process with key value pair
     * @param processed  List of processed objects hashCodes
     * @throws InvalidObjectException
     */
    private static void loadKeyValuePairForEncoding(final String key, final Object value,
            final List<SimpleEntry<String, Object>> objectList, final HashSet<Integer> processed)
            throws InvalidObjectException {
        if (value == null)
            return;
        if (isWrapperType(value.getClass()))
            objectList.add(new SimpleEntry<String, Object>(key, value));
        else
            objectToList(key, value, objectList, processed);
    }

    /**
     * While processing objects to map, loads value after serializing
     * 
     * @param key                  The key to used for creating key value pair
     * @param value                The value to process against the given key
     * @param objectList           The object list to process with key value pair
     * @param processed            List of processed objects hashCodes
     * @param serializerAnnotation
     * @throws InvalidObjectException
     */
    private static void loadKeyValuePairForEncoding(final String key, Object value,
            final List<SimpleEntry<String, Object>> objectList, final HashSet<Integer> processed,
            final JsonSerialize serializerAnnotation) throws InvalidObjectException {
        if (value == null)
            return;
        try {
            value = serialize(value, getSerializer(serializerAnnotation));
            if (value.toString().startsWith("\""))
                value = value.toString().substring(1, value.toString().length() - 1);
            objectList.add(new SimpleEntry<String, Object>(key, value));
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * List of classes that are wrapped directly. This information is need when
     * traversing object trees for reference matching
     */
    private static final Set<Class> WRAPPER_TYPES = new HashSet(
            Arrays.asList(Boolean.class, Character.class, Byte.class, Short.class, String.class, Integer.class,
                    Long.class, Float.class, Double.class, Void.class, File.class));

    /**
     * Checks if the given class can be wrapped directly
     * 
     * @param clazz The given class
     * @return true if the given class is an autoboxed class e.g., Integer
     */
    private static boolean isWrapperType(final Class clazz) {
        return WRAPPER_TYPES.contains(clazz) || clazz.isPrimitive() || clazz.isEnum();
    }
}