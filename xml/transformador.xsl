<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <!-- Salida en formato XML -->
  <xsl:output method="xml" indent="yes" encoding="UTF-8" />

  <!-- Plantilla principal -->
  <xsl:template match="/">
    <vehiculos>
      <!-- Recorre cada div con clase vehiculo-card -->
      <xsl:for-each select="//div[@class='vehiculo-card']">
        <vehiculo>
          <!-- Nombre del vehículo -->
          <nombre>
            <xsl:value-of select=".//div[@class='vehiculo-info']/h3" />
          </nombre>

          <!-- Matrícula -->
          <matricula>
            <xsl:value-of select=".//p[@class='matricula']" />
          </matricula>

          <!-- Tipo -->
          <tipo>
            <xsl:value-of select=".//span[@class='tipo']" />
          </tipo>

          <!-- Color -->
          <color>
            <xsl:value-of select=".//p[@class='color']" />
          </color>
        </vehiculo>
      </xsl:for-each>
    </vehiculos>
  </xsl:template>

</xsl:stylesheet>

