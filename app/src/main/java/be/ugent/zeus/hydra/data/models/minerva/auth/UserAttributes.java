/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 University Ghent
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *      The above copyright notice and this permission notice shall be included in all copies or
 *      substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package be.ugent.zeus.hydra.data.models.minerva.auth;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Minerva user attributes.
 *
 * @author Niko Strijbol
 * @author UGent
 */
@SuppressWarnings("unused")
public class UserAttributes implements Serializable {

    @SerializedName("mail")
    private ArrayList<String> email;

    @SuppressWarnings("SpellCheckingInspection")
    @SerializedName("jobcategory")
    private ArrayList<String> jobCategory;

    @SuppressWarnings("SpellCheckingInspection")
    @SerializedName("givenname")
    private ArrayList<String> givenName;

    @SerializedName("surname")
    private ArrayList<String> surName;

    private ArrayList<String> uid;

    public String getFullName() {
        return givenName + " " + surName;
    }

    public ArrayList<String> getEmail() {
        return email;
    }

    public ArrayList<String> getJobCategory() {
        return jobCategory;
    }

    public ArrayList<String> getGivenName() {
        return givenName;
    }

    public ArrayList<String> getSurName() {
        return surName;
    }

    public ArrayList<String> getUid() {
        return uid;
    }
}