// import React, { useEffect, useState } from "react";
// import NoCrashIcon from "@mui/icons-material/NoCrash";
// function OccupiedParkingCard() {
//   const [occupiedCount, setOccupiedCount] = useState(0);
//   useEffect(() => {
//     const fetchOccupiedCount = async () => {
//       try {
//         const jwtToken = localStorage.getItem("jwt");
//         const response = await fetch("http://localhost:8082/api/getAllFlats", {
//           headers: {
//             Authorization: `Bearer ${jwtToken}`,
//             "Content-Type": "application/json",
//           },
//         });
//         if (!response.ok) throw new Error("Failed to fetch flats");
//         const data = await response.json();
//         const occupiedFlats = data.filter((flat) => flat.occupied === true);
//         setOccupiedCount(occupiedFlats.length);
//       } catch (error) {
//         console.error("Error fetching flat count:", error);
//       }
//     };
//     fetchOccupiedCount();
//   }, []);
//   return (
//     <div className="flex flex-col  p-2 space-y-3 bg-yellow-200 rounded-md text-yellow-600  h-[100px]">
//       <div className="flex flex-row justify-between items-center">
//         <div className="font-bold text-4xl">{occupiedCount}</div>
//         <div className="rounded-full bg-white p-0.5">
//           <NoCrashIcon />
//         </div>
//       </div>
//       <div className="text-xs text-center">No of Parking lots Occupied</div>
//     </div>
//   );
// }

// export default OccupiedParkingCard;
import React, { useEffect, useState } from "react";
import NoCrashIcon from "@mui/icons-material/NoCrash";
import { CircularProgress } from "@mui/material";

function OccupiedParkingCard() {
  const [occupiedCount, setOccupiedCount] = useState(0);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchOccupiedCount = async () => {
      try {
        const jwtToken = localStorage.getItem("jwt");
        const response = await fetch("http://localhost:8082/api/getAllFlats", {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) throw new Error("Failed to fetch flats");

        const data = await response.json();
        const occupiedFlats = data.filter((flat) => flat.occupied === true);
        setOccupiedCount(occupiedFlats.length);
      } catch (error) {
        console.error("Error fetching flat count:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchOccupiedCount();
  }, []);

  return (
    <div className="flex flex-col p-4 space-y-3 bg-yellow-200 rounded-md text-yellow-600 h-[120px] shadow-lg transition-transform transform hover:scale-105">
      <div className="flex flex-row justify-between items-center">
        <div className="font-bold text-4xl">
          {loading ? <CircularProgress size={24} /> : occupiedCount}
        </div>
        <div className="rounded-full bg-white p-2">
          <NoCrashIcon sx={{ fontSize: "2rem", color: "#f1c40f" }} />
        </div>
      </div>
      <div className="text-xs text-center">Number of Parking Lots Occupied</div>
    </div>
  );
}

export default OccupiedParkingCard;
