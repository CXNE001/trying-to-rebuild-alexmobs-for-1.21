/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.tools;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ToJSON {
    private static final Set<Class> primitive = new HashSet<Class>();
    private static final Set<String> omitMethods = new HashSet<String>();

    public static String toJSON(Object obj) {
        StringBuilder builder = new StringBuilder();
        IntArrayList stack = IntArrayList.createIntArrayList();
        ToJSON.toJSONSub(obj, stack, builder);
        return builder.toString();
    }

    private static void toJSONSub(Object obj, IntArrayList stack, StringBuilder builder) {
        if (obj == null) {
            builder.append("null");
            return;
        }
        String className = obj.getClass().getName();
        if (className.startsWith("java.lang") && !className.equals("java.lang.String")) {
            builder.append("null");
            return;
        }
        int id = System.identityHashCode(obj);
        if (stack.contains(id)) {
            builder.append("null");
            return;
        }
        stack.push(id);
        if (obj instanceof ByteBuffer) {
            obj = NIOUtils.toArray((ByteBuffer)obj);
        }
        if (obj == null) {
            builder.append("null");
        } else if (obj instanceof String) {
            builder.append("\"");
            ToJSON.escape((String)obj, builder);
            builder.append("\"");
        } else if (obj instanceof Map) {
            Iterator it = ((Map)obj).entrySet().iterator();
            builder.append("{");
            while (it.hasNext()) {
                Map.Entry e = it.next();
                builder.append("\"");
                builder.append(e.getKey());
                builder.append("\":");
                ToJSON.toJSONSub(e.getValue(), stack, builder);
                if (!it.hasNext()) continue;
                builder.append(",");
            }
            builder.append("}");
        } else if (obj instanceof Iterable) {
            Iterator it = ((Iterable)obj).iterator();
            builder.append("[");
            while (it.hasNext()) {
                ToJSON.toJSONSub(it.next(), stack, builder);
                if (!it.hasNext()) continue;
                builder.append(",");
            }
            builder.append("]");
        } else if (obj instanceof Object[]) {
            builder.append("[");
            int len = Array.getLength(obj);
            for (int i = 0; i < len; ++i) {
                ToJSON.toJSONSub(Array.get(obj, i), stack, builder);
                if (i >= len - 1) continue;
                builder.append(",");
            }
            builder.append("]");
        } else if (obj instanceof long[]) {
            long[] a = (long[])obj;
            builder.append("[");
            for (int i = 0; i < a.length; ++i) {
                builder.append(String.format("0x%016x", a[i]));
                if (i >= a.length - 1) continue;
                builder.append(",");
            }
            builder.append("]");
        } else if (obj instanceof int[]) {
            int[] a = (int[])obj;
            builder.append("[");
            for (int i = 0; i < a.length; ++i) {
                builder.append(String.format("0x%08x", a[i]));
                if (i >= a.length - 1) continue;
                builder.append(",");
            }
            builder.append("]");
        } else if (obj instanceof float[]) {
            float[] a = (float[])obj;
            builder.append("[");
            for (int i = 0; i < a.length; ++i) {
                builder.append(String.format("%.3f", Float.valueOf(a[i])));
                if (i >= a.length - 1) continue;
                builder.append(",");
            }
            builder.append("]");
        } else if (obj instanceof double[]) {
            double[] a = (double[])obj;
            builder.append("[");
            for (int i = 0; i < a.length; ++i) {
                builder.append(String.format("%.6f", a[i]));
                if (i >= a.length - 1) continue;
                builder.append(",");
            }
            builder.append("]");
        } else if (obj instanceof short[]) {
            short[] a = (short[])obj;
            builder.append("[");
            for (int i = 0; i < a.length; ++i) {
                builder.append(String.format("0x%04x", a[i]));
                if (i >= a.length - 1) continue;
                builder.append(",");
            }
            builder.append("]");
        } else if (obj instanceof byte[]) {
            byte[] a = (byte[])obj;
            builder.append("[");
            for (int i = 0; i < a.length; ++i) {
                builder.append(String.format("0x%02x", a[i]));
                if (i >= a.length - 1) continue;
                builder.append(",");
            }
            builder.append("]");
        } else if (obj instanceof boolean[]) {
            boolean[] a = (boolean[])obj;
            builder.append("[");
            for (int i = 0; i < a.length; ++i) {
                builder.append(a[i]);
                if (i >= a.length - 1) continue;
                builder.append(",");
            }
            builder.append("]");
        } else if (obj.getClass().isEnum()) {
            builder.append(String.valueOf(obj));
        } else {
            builder.append("{");
            Method[] methods = obj.getClass().getMethods();
            ArrayList<Method> filteredMethods = new ArrayList<Method>();
            for (Method method : methods) {
                if (omitMethods.contains(method.getName()) || !ToJSON.isGetter(method)) continue;
                filteredMethods.add(method);
            }
            Iterator iterator = filteredMethods.iterator();
            while (iterator.hasNext()) {
                Method method = (Method)iterator.next();
                String name = ToJSON.toName(method);
                ToJSON.invoke(obj, stack, builder, method, name);
                if (!iterator.hasNext()) continue;
                builder.append(",");
            }
            builder.append("}");
        }
        stack.pop();
    }

    private static void invoke(Object obj, IntArrayList stack, StringBuilder builder, Method method, String name) {
        try {
            Object invoke = method.invoke(obj, new Object[0]);
            builder.append('\"');
            builder.append(name);
            builder.append("\":");
            if (invoke != null && primitive.contains(invoke.getClass())) {
                builder.append(invoke);
            } else {
                ToJSON.toJSONSub(invoke, stack, builder);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static void escape(String invoke, StringBuilder sb) {
        char[] ch;
        for (char c : ch = invoke.toCharArray()) {
            if (c < ' ') {
                sb.append(String.format("\\%02x", c));
                continue;
            }
            sb.append(c);
        }
    }

    private static String toName(Method method) {
        if (!ToJSON.isGetter(method)) {
            throw new IllegalArgumentException("Not a getter");
        }
        char[] name = method.getName().toCharArray();
        int ind = name[0] == 'g' ? 3 : 2;
        name[ind] = Character.toLowerCase(name[ind]);
        return new String(name, ind, name.length - ind);
    }

    private static boolean isGetter(Method method) {
        if (!Modifier.isPublic(method.getModifiers())) {
            return false;
        }
        if (!(method.getName().startsWith("get") || method.getName().startsWith("is") && method.getReturnType() == Boolean.TYPE)) {
            return false;
        }
        return method.getParameterTypes().length == 0;
    }

    static {
        primitive.add(Boolean.class);
        primitive.add(Byte.class);
        primitive.add(Short.class);
        primitive.add(Integer.class);
        primitive.add(Long.class);
        primitive.add(Float.class);
        primitive.add(Double.class);
        primitive.add(Character.class);
        omitMethods.add("getClass");
        omitMethods.add("get");
    }
}
