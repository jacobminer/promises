/*
 * Copyright (c) 2017 One Hill Technologies, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onehilltech.promises;

class ContinuationPromise <T> extends Promise <T>
{
  ContinuationPromise ()
  {
    super (null);
  }

  @SuppressWarnings ("unchecked")
  void continueWith (Promise <T> promise)
  {
    if (promise != null)
    {
      promise.then (resolved (new ResolveNoReturn<T> ()
      {
        @Override
        public void resolveNoReturn (T value)
        {
          onResolve (value);
        }
      }), rejected (new RejectNoReturn ()
      {
        @Override
        public void rejectNoReturn (Throwable reason)
        {
          onReject (reason);
        }
      }));
    }
    else
    {
      this.onResolve (null);
    }
  }

  void continueWithNull ()
  {
    this.onResolve (null);
  }

  void continueWith (Throwable t)
  {
    this.onReject (t);
  }
}
