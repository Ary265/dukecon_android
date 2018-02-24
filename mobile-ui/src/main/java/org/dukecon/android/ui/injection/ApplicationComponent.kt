package org.dukecon.android.ui.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import org.dukecon.android.ui.DukeconApplication
import org.dukecon.android.ui.features.main.MainComponent
import org.dukecon.android.ui.features.main.MainModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun mainComponent(mainModule: MainModule): MainComponent
    fun inject(dukeconApplication: DukeconApplication)

}