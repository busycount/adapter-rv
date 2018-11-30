# adapter-rv
&lt;Android> RecyclerView adapter


## Provide 

1. RecyclerView Adapter and Holder  
You need extends BaseRvAdapter<T> and BaseRvHolder<T>

2. DiffUtil  
Use RvDiffHelper to achieve diffUtil function

3. LineDivider  
A default itemDecoration of recyclerView

4. OnItemClickListener and OnItemLongClickListener

## Implementation
Step 1. Add the JitPack repository to your build file Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency, [Lastest release](https://github.com/busycount/adapter-rv/releases)
```
dependencies {
    implementation 'com.android.support:recyclerview-v7:Your Version'
    implementation 'com.github.busycount:adapter-rv:lastest'
}
```
