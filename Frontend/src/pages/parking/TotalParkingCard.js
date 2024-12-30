// import React from "react";
// import DirectionsCarIcon from "@mui/icons-material/DirectionsCar";
// function TotalParkingCard({ totalParking }) {
//   return (
//     <div className="flex flex-col  p-2 space-y-3 bg-green-200 rounded-md text-green-600  h-[100px]">
//       <div className="flex flex-row justify-between items-center">
//         <div className="font-bold text-4xl">{totalParking}</div>
//         <div className="rounded-full bg-white p-0.5">
//           <DirectionsCarIcon />
//         </div>
//       </div>
//       <div className="text-xs text-center">Total no of Parking Plots</div>
//     </div>
//   );
// }

// export default TotalParkingCard;
import React from "react";
import DirectionsCarIcon from "@mui/icons-material/DirectionsCar";

function TotalParkingCard({ totalParking }) {
  return (
    <div
      className="flex flex-col p-4 space-y-3 bg-green-200 rounded-md text-green-600 h-[120px] shadow-lg transition-transform transform hover:scale-105"
      title="Total Parking Information"
    >
      <div className="flex flex-row justify-between items-center">
        <div className="font-bold text-4xl">{totalParking}</div>
        <div className="rounded-full bg-white p-2">
          <DirectionsCarIcon
            sx={{ fontSize: "2rem", color: "#27ae60" }}
            aria-label="Parking Icon"
          />
        </div>
      </div>
      <div className="text-xs text-center">Total Number of Parking Plots</div>
    </div>
  );
}

export default TotalParkingCard;
