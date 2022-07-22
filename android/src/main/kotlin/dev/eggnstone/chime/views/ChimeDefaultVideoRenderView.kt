package dev.eggnstone.chime.views

import android.content.Context
import android.view.LayoutInflater
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.amazonaws.services.chime.sdk.meetings.audiovideo.video.DefaultVideoRenderView
import com.amazonaws.services.chime.sdk.meetings.audiovideo.video.VideoRenderView
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.platform.PlatformView

import dev.eggnstone.chime.R;

class ChimeDefaultVideoRenderView(context: Context?, id: Int) : PlatformView
{
    private val _defaultVideoRenderView: DefaultVideoRenderView = DefaultVideoRenderView(context!!)

    // private val textView: TextView

    private val view: View

    private val xmlVideoRenderView: VideoRenderView

    override fun getView(): View {
        return view
        // return textView
    }

    override fun dispose() {}

    val videoRenderView: VideoRenderView get() = xmlVideoRenderView

    init {
        view = LayoutInflater.from(context!!).inflate(R.layout.item_video, null)
        xmlVideoRenderView = view.findViewById(R.id.video_surface);
        // textView = TextView(context)
        // textView.textSize = 72f
        // textView.setBackgroundColor(Color.rgb(255, 255, 255))
        // textView.text = "Rendered on a native Android view (id: $id)"
    }
}