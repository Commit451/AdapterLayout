# AdapterLayout
ViewGroup backed by RecyclerView.Adapter = magic

[![Build Status](https://travis-ci.org/Commit451/AdapterLayout.svg?branch=master)](https://travis-ci.org/Commit451/AdapterLayout)

# Gradle Dependency
Easily reference the library in your Android projects using this dependency in your module's `build.gradle` file:

```Gradle
dependencies {
    compile 'com.commit451:adapterlayout:1.0.0'
}
```

# Usage
See the sample project for a full sample.
```java
//CheeseAdapter is a RecyclerView adapter
CheeseAdapter cheeseAdapter = new CheeseAdapter();
adapterLinearLayout adapterLinearLayout = (AdapterLinearLayout) findViewById(R.id.adapter_linear_layout);
adapterLinearLayout.setAdapter(cheeseAdapter);
```
Later, when you get your data set:
```java
ArrayList<Cheese> cheeses = loadCheeses();
//addAll is a method in our custom RecyclerView.Adapter
cheeseAdapter.addAll(cheeses);
```
At this point, the data will be added to the `LinearLayout` so long as your `RecyclerView.Adapter` works correctly. `onCreateViewHolder` and `onBindViewHolder` will be called as needed.

# But... Why?
This library is useful for displaying data sets that are repeating, but do not need to be recycled as they would be in a `RecyclerView`. This could be the case if you wanted to display a list of items within a `ScrollView`, or wanted to use a custom `ViewGroup` that did not extend `RecyclerView`.

# But Why RecyclerView Adapter?
Most developers should be using `RecyclerView` instead of `ListView` and should be familiar with creating a `RecyclerView.Adapter`. `RecyclerView.Adapter` also enforces the use of `ViewHolder`s which is better for performance. RecyclerView's adapter also allows for better responses to data structure changes via `notifyItemInserted`, `notifyItemRemoved` etc.

# Currently Created AdapterLayouts
- AdapterLinearLayout
- AdapterFlowLayout (currently in the sample app, can be copied out)

# Creating Your Own AdapterLayout
It is simple to create your own `ViewGroup` backed by a `RecyclerView.Adapter`. See `AdapterFlowLayout` in the sample app and `AdapterLayoutDelegate` in the library for an example of how to create one.

License
--------

    Copyright 2016 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
