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
package com.wpl.db.query.utils;

import java.util.concurrent.atomic.AtomicLong;

public class UniqueSequenceNumber {
	private static UniqueSequenceNumber sInstance = new UniqueSequenceNumber();
	private static ThreadLocal<UniqueSequenceNumber> sThreadInstance = new ThreadLocal<UniqueSequenceNumber>();

	private final AtomicLong mCurrent = new AtomicLong();

	public long next() {

		if (mCurrent.get() >= Long.MAX_VALUE) {
			reset();
		}

		return mCurrent.getAndIncrement();
	}

	public void reset() {
		mCurrent.set(0);
	}

	public static long globalNext() {
		return sInstance.next();
	}

	public static void globalReset() {
		sInstance.reset();
	}

	public static long currentThreadNext() {
		if (sThreadInstance.get() == null) {
			sThreadInstance.set(new UniqueSequenceNumber());
		}

		return sThreadInstance.get().next();
	}

	public static void currentThreadReset() {
		if (sThreadInstance.get() == null) {
			sThreadInstance.set(new UniqueSequenceNumber());
		}

		sThreadInstance.get().reset();
	}
}
