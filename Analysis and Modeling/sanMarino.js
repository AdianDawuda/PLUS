/**** Start of imports. If edited, may not auto-convert in the playground. ****/
var s2 = ee.ImageCollection("COPERNICUS/S2_SR_HARMONIZED"),
    geometry = 
    /* color: #d63000 */
    /* shown: false */
    /* displayProperties: [
      {
        "type": "rectangle"
      }
    ] */
    ee.Geometry.Polygon(
        [[[12.38488377853439, 43.99770114718763],
          [12.38488377853439, 43.888685087118866],
          [12.529079335175014, 43.888685087118866],
          [12.529079335175014, 43.99770114718763]]], null, false);
/***** End of imports. If edited, may not auto-convert in the playground. *****/
// Define Area of Interest
var aoi = geometry;

// Define and display the image
var image = s2.filterBounds(aoi)
  .filterDate('2018-01-01', '2018-12-31')
  .map(cloudMask)
  .median()
  .clip(aoi);
Map.addLayer(image, {bands: ['B4', 'B3', 'B2'], min: 0, max: [5000, 3000, 2000]}, 'Sentinel-2');

// Cloud masking function
function cloudMask(image){
  var scl = image.select('SCL');
  var mask = scl.eq(3).or(scl.gte(7).and(scl.lte(10)));
  return image.updateMask(mask.eq(0));
}

// Export parameters
var exportParams = {
  image: image,
  description: 'San_Marino_zoom_level_10',
  scale: 10,
  region: aoi,
  folder: 'gee_exports'
};

// Export the image
Export.image.toDrive(exportParams);