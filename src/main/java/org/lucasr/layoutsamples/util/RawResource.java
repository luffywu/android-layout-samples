/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lucasr.layoutsamples.util;

import android.content.Context;
import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public final class RawResource {
    public static JSONArray getAsJSON(Context context, int id) throws IOException {
        InputStreamReader reader = null;

        try {
            final Resources res = context.getResources();
            final InputStream is = res.openRawResource(id);
            if (is == null) {
                return null;
            }

            reader = new InputStreamReader(is);

            final char[] buffer = new char[1024];
            final StringWriter s = new StringWriter();

            int n;
            while ((n = reader.read(buffer, 0, buffer.length)) != -1) {
                s.write(buffer, 0, n);
            }

            return new JSONArray(s.toString());
        } catch (JSONException e) {
            return null;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}