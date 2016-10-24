package lt.markmerkk.mock_app.dagger.scopes

import javax.inject.Scope
import java.lang.annotation.Retention

import java.lang.annotation.RetentionPolicy.RUNTIME

@Scope
@Retention(RUNTIME)
annotation class PerActivityScope
