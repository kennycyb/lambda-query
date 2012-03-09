/*
 * Copyright 2011 Kenny Chong (wongpeiling.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wpl.db.query;

import java.util.Date;
import java.util.Set;



public class Person {

	private String mFirstName;
	private String mLastName;
	private int mAge;
	private Date mBirthday;
	private Set<Contact> mContacts;

	public int getAge() {
		return mAge;
	}

	public void setAge(int age) {
		mAge = age;
	}

	public String getFirstName() {
		return mFirstName;
	}

	public void setFirstName(String firstName) {
		mFirstName = firstName;
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String lastName) {
		mLastName = lastName;
	}

	public Date getBirthday() {
		return mBirthday;
	}

	public void setBirthday(Date birthday) {
		mBirthday = birthday;
	}

	public Set<Contact> getContacts() {
		return mContacts;
	}

	public void setContacts(Set<Contact> contacts) {
		mContacts = contacts;
	}

}
