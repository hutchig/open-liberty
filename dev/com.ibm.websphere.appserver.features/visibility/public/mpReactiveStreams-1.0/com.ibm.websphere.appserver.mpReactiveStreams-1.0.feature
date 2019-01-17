-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=com.ibm.websphere.appserver.mpReactiveStreams-1.0
visibility=public
singleton=true
IBM-App-ForceRestart: install, \
 uninstall
IBM-ShortName: mpReactiveStreams-1.0
Subsystem-Name: MicroProfile Reactive Streams 1.0
IBM-API-Package: \
  org.eclipse.microprofile.reactive.streams.operators; type="stable", \
  org.eclipse.microprofile.reactive.streams.operators.spi; type="stable", \
  org.eclipse.microprofile.reactive.streams.operators.core; type="stable", \
  org.reactivestreams; type="stable";
-features=\
  com.ibm.websphere.appserver.org.eclipse.microprofile.reactive.streams.operators-1.0
-bundles=\
  com.ibm.ws.microprofile.reactive.streams.operators; apiJar=false; location:="lib/", \
  com.ibm.ws.smallrye.reactive.streams.operators.1.0; apiJar=false; location:="dev/api/third-party/,lib/", \
  com.ibm.websphere.io.reactivex.rxjava2.1.0; apiJar=false; location:="dev/api/third-party/,lib/"
kind=noship
edition=full