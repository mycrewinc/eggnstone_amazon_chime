import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';

/// A view where local or remote video gets rendered
class ChimeDefaultVideoRenderView extends StatefulWidget {
  /// Event to be called when the view gets created
  final ValueChanged<int>? onPlatformViewCreated;

  /// Creates a [ChimeDefaultVideoRenderView].
  /// Optional: [onPlatformViewCreated] event to be called when the view gets created.
  ChimeDefaultVideoRenderView({Key? key, this.onPlatformViewCreated})
      : super(key: key);

  @override
  _ChimeDefaultVideoRenderViewState createState() =>
      _ChimeDefaultVideoRenderViewState();
}

class _ChimeDefaultVideoRenderViewState
    extends State<ChimeDefaultVideoRenderView> {
  @override
  Widget build(BuildContext context) {
    if (Platform.isAndroid) {
      return PlatformViewLink(
        viewType: 'ChimeDefaultVideoRenderView',
        surfaceFactory:
            (BuildContext context, PlatformViewController controller) {
          return AndroidViewSurface(
            controller: controller as AndroidViewController,
            gestureRecognizers: const <Factory<OneSequenceGestureRecognizer>>{},
            hitTestBehavior: PlatformViewHitTestBehavior.opaque,
          );
        },
        onCreatePlatformView: (PlatformViewCreationParams params) {
          final AndroidViewController controller =
              PlatformViewsService.initExpensiveAndroidView(
            id: params.id,
            viewType: 'ChimeDefaultVideoRenderView',
            layoutDirection: TextDirection.ltr,
            creationParams: 0,
            creationParamsCodec: const StandardMessageCodec(),
            onFocus: () => params.onFocusChanged,
          );
          controller
              .addOnPlatformViewCreatedListener(params.onPlatformViewCreated);
          controller.create();
          widget.onPlatformViewCreated?.call(params.id);
          return controller;
        },
      );
      // return AndroidView(
      //   viewType: 'ChimeDefaultVideoRenderView',
      //   onPlatformViewCreated: (int viewId) =>
      //       widget.onPlatformViewCreated?.call(viewId),
      // );//
    } else if (Platform.isIOS) {
      return UiKitView(
        viewType: 'ChimeDefaultVideoRenderView',
        onPlatformViewCreated: (int viewId) =>
            widget.onPlatformViewCreated?.call(viewId),
      );
    } else
      throw Exception('Not implemented');
  }
}
