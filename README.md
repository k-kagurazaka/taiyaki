# Taiyaki

Show DialogFragment safely.


## Download

```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.k-kagurazaka:taiyaki:1.0.0'
}
```


## Usage

First, define your own `DialogRequest` class.

```kotlin
sealed class MainDialogRequest : DialogRequest

data class DialogWithParamRequest(val param: String) : MainDialogRequest()

object DialogWithoutParamRequest : MainDialogRequest()
```

Next, implement `HasTaiyaki` interafce to your activity.

```kotlin
class MainActivity : AppCompatActivity(), HasTaiyaki<MainDialogRequest> {
    override val taiyaki = Taiyaki(this)

    // Handling your DialogRequest
    override fun onDialogRequest(request: MainDialogRequest) {
        when (request) {
            is DialogWithParamRequest -> TextDialogFragment.newInstance("Param: ${request.param}")
            is DialogWithoutParamRequest -> TextDialogFragment.newInstance("No Param")
        }.show(supportFragmentManager, null)
    }
}
```

Finally, register `ActivityLifeCycleCallbacks` to your `Application` class.

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(Taiyaki.LifeCycleCallbacks)
    }
}
```

You are done! Now, you can send requests to show `DialogFragment`.

```kotlin
// in Activity
fun showDialogWithoutParam() {
    taiyaki.request(DialogWithoutParamRequest)
}
```

## License

    The MIT License (MIT)

    Copyright (c) 2017 Keita Kagurazaka

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
