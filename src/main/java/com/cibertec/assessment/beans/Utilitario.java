package com.cibertec.assessment.beans;

public class Utilitario {

	public int[] convertStringInIntegerArray(String xPoints) {

		String cleanedXPoints = xPoints.substring(1, xPoints.length() - 1);

		String[] partsX = cleanedXPoints.split(", ");
		int[] intArrayX = new int[partsX.length];
		for (int i = 0; i < partsX.length; i++) {
			intArrayX[i] = Integer.parseInt(partsX[i]);
		}
		return intArrayX;

	}
	public boolean unionlineas(int abscisa1, int ordenada1, int abscisa2, int ordenada2, int abscisa3, int ordenada3,
			int abscisa4, int ordenada4) {

		return ((orientacionRayo(abscisa3, ordenada3, abscisa4, ordenada4, abscisa1, ordenada1) > 0
				&& orientacionRayo(abscisa3, ordenada3, abscisa4, ordenada4, abscisa2, ordenada2) < 0)
				|| (orientacionRayo(abscisa3, ordenada3, abscisa4, ordenada4, abscisa1, ordenada1) < 0
						&& orientacionRayo(abscisa3, ordenada3, abscisa4, ordenada4, abscisa2, ordenada2) > 0))
				&& ((orientacionRayo(abscisa1, ordenada1, abscisa2, ordenada2, abscisa3, ordenada3) > 0
						&& orientacionRayo(abscisa1, ordenada1, abscisa2, ordenada2, abscisa4, ordenada4) < 0)
						|| (orientacionRayo(ordenada1, ordenada1, abscisa2, ordenada2, abscisa3, ordenada3) < 0
								&& orientacionRayo(abscisa1, ordenada1, abscisa2, ordenada2, abscisa4, ordenada4) > 0));
	}
	public boolean intersectoEntreFiguras(int[] xPoints, int[] yPoints, PolygonBean polygon) {
		int cantidad = polygon.getNpoints();

		boolean intersecta = false;

		// linea del cuadrado intersecta con alguna linea del poligono
		for (int i = 0, j = cantidad - 1; i < cantidad; j = i++) {
			for (int n = 0; n < xPoints.length; n++) {
				if (unionlineas(xPoints[n], yPoints[n], xPoints[(n + 1) % xPoints.length],
						yPoints[(n + 1) % xPoints.length], 
						polygon.getXPoints()[i], polygon.getYPoints()[i], polygon.getXPoints()[j],
						polygon.getYPoints()[j])) {
					intersecta = true;
					return intersecta;

				}
			}
		}
		for (int i = 0, c = cantidad - 1; i < cantidad; c = i++) {
			if (puntoEnPoligono(xPoints[0], yPoints[0], polygon.getXPoints()[i], polygon.getYPoints()[i],
					polygon.getXPoints()[c], polygon.getYPoints()[c])) {
				intersecta = true;
				return intersecta;
			}
		}

		return intersecta;
	}

	public int orientacionRayo(int x1, int y1, int x2, int y2, int x3, int y3) {
		return (x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1);
	}

	public boolean puntoEnPoligono(int pX, int pY, int abscisa1, int ordenada1, int abscisa2, int ordenada2) {
		return (ordenada1 > pY) != (ordenada2 > pY)
				&& pX < (abscisa2 - abscisa1) * (pY - ordenada1) / (ordenada2 - ordenada1) + abscisa1;
	}

	

}
