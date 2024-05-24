import { SongData } from "../model/SongData";

//AppState - המידע עצמו הקיים ברמת האפליקציה והרידקס
export class SongState {
    public allSongs: SongData[] = [];
}

//ActionType - סוג הפעולה שניתן לבצע על הסטייט ויבוצע ברשימה אחודה סגורה
export enum SongActionType {
    addSong = "addSong",
    deleteSong = "deleteSong",
    updateSong = "updateSong",
    getSongList = "getSongList",
}

//Action - אובייקט המכיל את המידע הדרוש עבור ביצוע הפעולה
//אובייקט זה נשלח לרידקס בעת ביצוע פעולת העידכון
//לדוגמא: אם אנחנו רוצים להוסיף שיר , אז יש לשלוח אובייט המכיל את סוג הפעולה המתאים (הוספת שיר)
//ואת המידע עצמו שאנו צריכים על מנת להוסיף שיר
//האובייקט מכיל שני מאפיינים
//Action Type - סוג הפעולה לבצוע
//payload - המידע עצמו, שהוא עצמו יכול להיות אפוציונלי, לדוגמא קבלת כל השירים
export interface SongAction {
    type: SongActionType,
    payload?: any
}

//action creator : אלה למעשה הפונקציות שאותם אנו נחצין ונפעיל, שייפעלו עפ"י הכללים שרשמנו למעלה
export function addSongAction(newSong: SongData): SongAction {
    return { type: SongActionType.addSong, payload: newSong };
}

export function deleteSongAction(id: string): SongAction {
    return { type: SongActionType.deleteSong, payload: id };
}

export function updateSongsAction(song: SongData): SongAction {
    return { type: SongActionType.updateSong, payload: song };
}

export function getAllSongsAction(songs: SongData[]): SongAction {
    return { type: SongActionType.getSongList, payload: songs }
}

//reducer: פונקציה המבצעת בפועל את הפעולות שאנו רוצים לבצע
// לפונקציה יש חתימה ייחודית שאנו חייבים לממש
//הפונקציה תחזיר את הסטייט המעודכן
//לא ניתן לגשת לרדיוסר דרך הקוד, הרידקס מפעיל את הפונקציה בעצמו
export function SongReducer(currentState: SongState = new SongState(), action: SongAction): SongState {
    const newState = { ...currentState }; //יצירת העתק מושלם של המצב הנוכחי
    switch (action.type) {
        case SongActionType.addSong:
            //הוסף למערך של השירים, את השיר שמגיע בתוך הפיילוד
            newState.allSongs = [...newState.allSongs, action.payload];
            break;
        case SongActionType.deleteSong:
            newState.allSongs = [...newState.allSongs].filter((item) => item.id !== action.payload);
            break;
        case SongActionType.updateSong:
            newState.allSongs = [...newState.allSongs].filter((item) => item.id !== action.payload.id);
            newState.allSongs = [...newState.allSongs, action.payload];
            break;
        case SongActionType.getSongList:
            newState.allSongs = action.payload;
            break;
    }
    //החזרת העידכון והפיכתו למצב הנוכחי
    return newState;
}
