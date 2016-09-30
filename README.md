# AdapterLayout
ViewGroup backed by RecyclerView.Adapter, allowing for easy data set manipulation with non recycling views. Aka RecyclerView minus the recycling.

[![Build Status](https://travis-ci.org/Commit451/AdapterLayout.svg?branch=master)](https://travis-ci.org/Commit451/AdapterLayout) [![](https://jitpack.io/v/Commit451/AdapterLayout.svg)](https://jitpack.io/#Commit451/AdapterLayout)

# Dependency
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and within your application `build.gradle`

```gradle
dependencies {
    compile 'com.github.Commit451.AdapterLayout:adapterlayout:1.0.3'
}
```
and for [FlowLayout](https://github.com/blazsolar/FlowLayout) support
```gradle
dependencies {
    compile 'com.github.Commit451.AdapterLayout:adapterflowlayout:1.0.3'
}
```

# Usage
See the sample project for full usage.
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
- AdapterFlowLayout (separate dependency)

# Creating Your Own AdapterLayout
It is simple to create your own `ViewGroup` backed by a `RecyclerView.Adapter`. See the `AdapterFlowLayout` library and `AdapterLayoutDelegate` in the library for an example of how to create one.

# Limitations
- Within a `RecyclerView.Adapter`, you can call `getAdapterPosition()`. This will always return -1 since there is actually never an associated RecyclerView.

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
