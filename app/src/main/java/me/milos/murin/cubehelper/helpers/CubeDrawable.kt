package me.milos.murin.cubehelper.helpers

import android.graphics.*
import android.graphics.drawable.Drawable
import me.milos.murin.cubehelper.data.LastLayer

class CubeDrawable (private val paints: LastLayer) : Drawable() {
    private val linePaint: Paint = Paint().apply { setARGB(255, 33, 33, 33) }

    override fun draw(canvas: Canvas) {

        val x = bounds.width() / 11

        // Middle cube part
        // back
        canvas.drawRect((bounds.left + x).toFloat(), (bounds.top + x).toFloat(), (bounds.left + 10 * x).toFloat(), (bounds.top + 10 * x).toFloat(), linePaint)
        // front
        for (i in 0..2) {
            for (j in 0..2) {
                val left = bounds.left + x + (i * 3 * x)
                val top = bounds.top + x + (j * 3 * x)
                canvas.drawRect(Rect(left + (2 * x / 10), top + (2 * x / 10),
                                     left + (28 * x / 10), top + (28 * x / 10)), paints.getTop(i, j))
            }
        }

        // Edges of cube
        // background
        val cornersBack = getCornersBackPath(x)
        // foreground
        val cornersFront = getCornersFrontPath(x)

        for (i in 1..4) {
            canvas.drawPath(cornersBack, linePaint)
            canvas.drawPath(cornersFront, paints.getCi(i - 1))

            rotate90(cornersBack, 5.5 * x, 5.5 * x)
            rotate90(cornersFront, 5.5 * x, 5.5 * x)
        }

        scale(cornersFront, x) // flip to reuse foreground path
        transformX(cornersFront, 6 * x)

        for (i in 1..4) {
            canvas.drawPath(cornersFront, paints.getCiP2(i - 1))

            rotate90(cornersFront, 5.5 * x, 5.5 * x)
        }
        // middle edges
        canvas.drawRect(RectF(bounds.left + (42f * x / 10), bounds.top + (2f * x / 10),
                             bounds.left + (68f * x / 10), bounds.top + (9f * x / 10)), paints.getMV(0))
        canvas.drawRect(RectF(bounds.left + (2f * x / 10), bounds.top + (42f * x / 10),
                             bounds.left + (9f * x / 10), bounds.top + (68f * x / 10)), paints.getMH(0))
        canvas.drawRect(RectF(bounds.left + (42f * x / 10), bounds.top + (101f * x / 10),
                             bounds.left + (68f * x / 10), bounds.top + (108f * x / 10)), paints.getMV(1))
        canvas.drawRect(RectF(bounds.left + (101f * x / 10), bounds.top + (42f * x / 10),
                             bounds.left + (108f * x / 10), bounds.top + (68f * x / 10)), paints.getMH(1))
    }

    private fun transformX(path: Path, x: Int) {
        val matrix = Matrix()
        matrix.postTranslate(x.toFloat(), 0f)
        path.transform(matrix)
    }

    private fun rotate90(path: Path, px: Double, py: Double) {
        val matrix = Matrix()
        matrix.setRotate(90f, px.toFloat(), py.toFloat())
        path.transform(matrix)
    }

    private fun scale(path: Path, x: Int) {
        val matrix = Matrix()
        matrix.setScale(-1f, 1f, 2.5f * x, 0.5f * x)
        path.transform(matrix)
    }


    private fun getCornersBackPath(x: Int): Path {
        val cornersBack = Path()
        cornersBack.moveTo((14 * x / 10).toFloat(), 0F)
        cornersBack.lineTo((96 * x / 10).toFloat(), 0f)
        cornersBack.lineTo((10 * x).toFloat(), x.toFloat())
        cornersBack.lineTo(x.toFloat(), x.toFloat())
        cornersBack.close()
        return cornersBack
    }

    private fun getCornersFrontPath(x: Int): Path {
        val cornersFront = Path()
        cornersFront.moveTo((16 * x / 10).toFloat(), (2 * x / 10).toFloat())
        cornersFront.lineTo((38 * x / 10).toFloat(), (2 * x / 10).toFloat())
        cornersFront.lineTo((38 * x / 10).toFloat(), (9 * x / 10).toFloat())
        cornersFront.lineTo((13 * x / 10).toFloat(), (9 * x / 10).toFloat())
        cornersFront.close()
        return cornersFront
    }

    override fun setAlpha(alpha: Int) {

    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
}